# ch03 Sequence Diagrams — JSP 내장객체 (Implicit Objects)

How each ch03 page flows between the **Browser**, **Tomcat**, and the JSP pages.
Open this file with Markdown Preview (VS Code: `Cmd+Shift+V`) to render the Mermaid diagrams.

---

## 1. `request` — GET / POST form submission

```mermaid
sequenceDiagram
    autonumber
    actor U as User
    participant B as Browser
    participant T as Tomcat
    participant P1 as 1.request.jsp
    participant R1 as proc/result1.jsp
    participant R2 as proc/result2.jsp
    participant R3 as proc/result3.jsp

    U->>B: open /ch03/1.request.jsp
    B->>T: GET /ch03/1.request.jsp
    T->>P1: execute
    P1->>P1: request.getHeader("User-Agent"),<br/>getProtocol(), getRequestURL(), getRemoteAddr() ...
    P1-->>B: HTML (3 forms + request info)

    Note over U,R1: GET form — data goes in the URL (query string)
    U->>B: fill uid/name/age, submit
    B->>T: GET /proc/result1.jsp?uid=..&name=..&age=..
    T->>R1: execute
    R1->>R1: request.getQueryString()<br/>request.getParameter("uid" / "name" / "age")
    R1-->>B: HTML with received values

    Note over U,R2: POST form — data goes in the request body
    U->>B: fill uid/name/age, submit
    B->>T: POST /proc/result2.jsp (body: uid=..&name=..&age=..)
    T->>R2: execute
    R2->>R2: getQueryString() → null (nothing in URL)<br/>getParameter(...) reads the body
    R2-->>B: HTML (back link only)

    Note over U,R3: 확인문제 form — multi-value checkbox
    U->>B: fill form, submit
    B->>T: POST /proc/result3.jsp
    T->>R3: execute
    R3->>R3: getParameter("uid","pass","name","birth","gender","addr")<br/>getParameterValues("hobby") → String[]
    R3-->>B: HTML with all values
```

> ⚠️ In `result3.jsp`, if no gender or no hobby is selected, `gender.equals(...)` / `for (hobby : hobbies)` throws `NullPointerException` → Tomcat shows `/error/500.jsp` (see section 6).

---

## 2. `response` — redirect & file download

```mermaid
sequenceDiagram
    autonumber
    participant B as Browser
    participant T as Tomcat
    participant RD1 as proc/redirect1.jsp
    participant RD2 as proc/redirect2.jsp
    participant RF as proc/responseFile.jsp
    participant P1 as 1.request.jsp
    participant N as naver.com

    Note over B,P1: Redirect inside the same server
    B->>T: GET /proc/redirect1.jsp
    T->>RD1: execute
    RD1->>RD1: response.sendRedirect("../1.request.jsp")
    RD1-->>B: 302 Found, Location: /ch03/1.request.jsp
    B->>T: NEW request: GET /ch03/1.request.jsp
    T->>P1: execute
    P1-->>B: HTML (address bar changes to 1.request.jsp)

    Note over B,N: Redirect to an external server — allowed
    B->>T: GET /proc/redirect2.jsp
    T->>RD2: execute
    RD2-->>B: 302 Found, Location: https://naver.com
    B->>N: NEW request: GET https://naver.com
    N-->>B: Naver page

    Note over B,RF: File download
    B->>T: GET /proc/responseFile.jsp
    T->>RF: execute
    RF->>RF: response.setHeader("Content-Type", "application/octet-stream")
    RF-->>B: body with Content-Type: application/octet-stream
    B->>B: not rendered — saved as a file
```

**Key point:** a redirect is **2 requests**. The server tells the browser *"go ask over there"*, so the URL changes and the original `request` object is gone.

---

## 3. `pageContext` — include & forward

```mermaid
sequenceDiagram
    autonumber
    participant B as Browser
    participant T as Tomcat (JSP compiler)
    participant PC as 3.pageContext.jsp
    participant H as inc/header.jsp
    participant F as inc/footer.jsp
    participant FW1 as proc/forward1.jsp
    participant FW2 as proc/forward2.jsp
    participant P1 as 1.request.jsp

    Note over T,H: Static include — at COMPILE time
    T->>H: <%@ include file="./inc/header.jsp" %>
    H-->>T: source code pasted into 3.pageContext servlet

    B->>T: GET /ch03/3.pageContext.jsp
    T->>PC: execute (header already merged in)

    Note over PC,F: Dynamic include — at RUN time
    PC->>H: pageContext.include("./inc/header.jsp")
    H-->>PC: header HTML output
    PC->>F: pageContext.include("./inc/footer.jsp")
    F-->>PC: footer HTML output
    PC-->>B: HTML (header ×2, footer ×1)

    Note over B,P1: Forward — server-side move, ONE request
    B->>T: GET /proc/forward1.jsp
    T->>FW1: execute
    FW1->>P1: pageContext.forward("../1.request.jsp")<br/>(same request/response objects)
    P1-->>B: 1.request.jsp HTML<br/>(address bar still shows forward1.jsp)

    Note over B,FW2: Forward to an external URL — NOT possible
    B->>T: GET /proc/forward2.jsp
    T->>FW2: execute
    FW2->>T: pageContext.forward("https://naver.com")
    T-->>B: error — forward only works within this web app
```

> ⚠️ The `<a href="./proc/forward1.jsp"></a>` links in `3.pageContext.jsp` have no text, so they're invisible on the page. Type the URL directly, or add link text.

### Redirect vs Forward

| | `response.sendRedirect()` | `pageContext.forward()` |
|---|---|---|
| Requests | 2 (browser re-requests) | 1 (server moves internally) |
| Address bar | changes | stays the same |
| `request` attributes | lost | kept |
| External site | ✅ possible | ❌ not possible |

---

## 4. `application` — server info & web.xml params

```mermaid
sequenceDiagram
    autonumber
    participant B as Browser
    participant T as Tomcat
    participant W as WEB-INF/web.xml
    participant A as 4.application.jsp

    Note over T,W: On server startup
    T->>W: read <context-param> param1=1, param2=2
    W-->>T: stored in ServletContext (= application)

    B->>T: GET /ch03/4.application.jsp
    T->>A: execute
    A->>T: application.getServerInfo()
    T-->>A: "Apache Tomcat/10.1.x"
    A->>T: application.getInitParameter("param1" / "param2")
    T-->>A: "1", "2"
    A->>T: application.getRealPath("./4.application.jsp")
    T-->>A: absolute file path on disk
    A-->>B: HTML
```

---

## 5. `session` — per-browser storage

```mermaid
sequenceDiagram
    autonumber
    participant B as Browser (Edge or Chrome)
    participant T as Tomcat
    participant S as Session store
    participant SP as 5.session.jsp
    participant VO as sub1.UserVO

    B->>T: GET /ch03/5.session.jsp (no JSESSIONID cookie yet)
    T->>S: create new session
    S-->>T: session id
    T->>SP: execute
    SP->>SP: session.getId()
    SP->>SP: request.getHeader("User-Agent")
    alt User-Agent contains "Edg"
        SP->>VO: new UserVO("a101", "김유신", 23)
    else other browser (Chrome ...)
        SP->>VO: new UserVO("a102", "김춘추", 21)
    end
    SP->>S: session.setAttribute("user", user)
    SP->>S: session.getAttribute("user")
    S-->>SP: UserVO
    SP->>VO: getUid(), getName(), getAge()
    SP-->>B: HTML + Set-Cookie: JSESSIONID=...

    Note over B,S: Next request from the SAME browser
    B->>T: GET ... (Cookie: JSESSIONID=...)
    T->>S: find existing session by id
    Note over B,S: Edge and Chrome have different cookies → different sessions
```

---

## 6. `exception` — error pages via web.xml

```mermaid
sequenceDiagram
    autonumber
    participant B as Browser
    participant T as Tomcat
    participant W as web.xml &lt;error-page&gt;
    participant ERR as proc/error.jsp
    participant E404 as error/404.jsp
    participant E500 as error/500.jsp

    Note over B,E404: 404 — page does not exist
    B->>T: GET /proc/test.jsp
    T->>T: file not found
    T->>W: lookup error-code 404
    W-->>T: /error/404.jsp
    T->>E404: forward
    E404-->>B: "페이지를 찾을 수 없습니다."

    Note over B,E500: 500 — exception while running
    B->>T: GET /proc/error.jsp
    T->>ERR: execute
    ERR->>ERR: 10 / 0 → ArithmeticException
    ERR-->>T: exception thrown
    T->>W: lookup error-code 500
    W-->>T: /error/500.jsp
    T->>E500: forward (isErrorPage="true" → exception object available)
    E500->>E500: exception.getClass().getName()<br/>exception.getMessage() → "/ by zero"
    E500-->>B: "일시적으로 서버에 문제가 생겼습니다."
```

---

## 7. Scope test — page / request / session / application

```mermaid
sequenceDiagram
    autonumber
    participant B as Browser
    participant T as Tomcat
    participant ST as 7.scopeTest.jsp
    participant SR as proc/scopeResult.jsp

    B->>T: GET /ch03/7.scopeTest.jsp
    T->>ST: execute
    ST->>ST: pageContext.setAttribute("name","김유신")
    ST->>ST: request.setAttribute("name","김춘추")
    ST->>ST: session.setAttribute("name","장보고")
    ST->>ST: application.setAttribute("name","강감찬")
    ST->>ST: print 4 values (this output is discarded by forward)
    ST->>SR: pageContext.forward("./proc/scopeResult.jsp")<br/>(same request, NEW pageContext)
    SR->>SR: pageContext → null (page scope ended)
    SR->>SR: request → 김춘추 (same request)
    SR->>SR: session → 장보고
    SR->>SR: application → 강감찬
    SR-->>B: HTML (address bar still 7.scopeTest.jsp)

    Note over B,SR: Open scopeResult.jsp directly (a new request)
    B->>T: GET /ch03/proc/scopeResult.jsp
    T->>SR: execute
    SR-->>B: page → null, request → null,<br/>session → 장보고 (same browser), application → 강감찬 (everyone)
```

| Scope | Lives until | Shared with |
|---|---|---|
| `pageContext` | this JSP finishes | this page only |
| `request` | the response is sent (survives forward) | pages in the same request |
| `session` | browser closes / timeout | same browser (JSESSIONID) |
| `application` | server stops | all users |

> ⚠️ The link in `scopeResult.jsp` is `./ch03/proc/scopeResult.jsp`. Because the address bar is still `/ch03/7.scopeTest.jsp`, it resolves to `/ch03/ch03/proc/scopeResult.jsp` → 404. Use `/ch03/proc/scopeResult.jsp` instead.
