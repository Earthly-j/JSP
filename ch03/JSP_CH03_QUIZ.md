# JSP Chapter 3 Quiz — Fill in the Blanks

Covers: request, response, pageContext, application, session, exception, scope (4 scopes), forward vs redirect.

---

## Q1 — request implicit object (1.request.jsp, result1/2/3.jsp)

The `request` object holds client request info. Fill in the missing calls:

```jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 단일 파라미터 값 받기 (텍스트, 비밀번호, 라디오, 셀렉트)
	String uid = request.____("uid");
	String pass = request.____("pass");
	String gender = request.____("gender");
	String addr = request.____("addr");

	// 다중 파라미터 값 받기 (체크박스 등 name이 같은 값 여러 개)
	String[] hobbies = request.____("hobby");

	// 쿼리 스트링 전체 (GET 요청 주소 뒤에 붙은 ?이후 부분)
	String query = request.____();
%>

<p>
	헤더: <%= request.____("User-Agent") %><br>
	프로토콜: <%= request.____() %><br>
	서버이름: <%= request.____() %><br>
	요청주소: <%= request.____() %><br>
	요청경로: <%= request.____() %><br>
	클라이언트 IP: <%= request.____() %><br>
</p>
```

**Answers:** getParameter, getParameter, getParameter, getParameter, getParameterValues, getQueryString; getHeader, getProtocol, getServerName, getRequestURL, getRequestURI, getRemoteAddr

**Key point:** GET sends data via query string (visible in URL); POST sends data in request body (hidden, better for security/large data).

---

## Q2 — response vs forward vs redirect (2.response.jsp, proc/redirect1.jsp, proc/forward1.jsp)

```jsp
<%-- 2.response.jsp --%>
<a href="./proc/redirect1.jsp">리다이렉트</a>

<%-- proc/redirect1.jsp --%>
<%
	response.____("../1.request.jsp");
%>

<%-- proc/forward1.jsp --%>
<%
	pageContext.____("../1.request.jsp");
%>
```

**Answers:** `sendRedirect`, `forward`

**Key differences:**
- `response.sendRedirect(url)`: tells the **browser** to make a brand-new request to the URL. URL in address bar changes; it's a new request (request scope data is lost). Slower (2 round trips).
- `pageContext.forward(url)`: server-side forward to another resource. URL stays the same; request object is preserved (request scope data carried over). Faster (1 round trip).

---

## Q3 — pageContext dynamic include vs static include (3.pageContext.jsp)

```jsp
<%-- 정적 include (번역 시점에 포함) --%>
<%@ include file="./inc/header.jsp" %>

<%-- 동적 include (요청 시점에 포함) --%>
<%
	pageContext.____("./inc/header.jsp");
	pageContext.____("./inc/footer.jsp");
%>
```

**Answer:** `include`, `include`

**Key point:** `<%@ include %>` is static (file content copied in at translation time); `pageContext.include()` is dynamic (runs at request time, can include dynamic URLs and params).

---

## Q4 — application scope (4.application.jsp, web.xml)

```jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3>서버 정보</h3>
<%= application.____() %>

<h3>전역 파라미터 (web.xml context-param)</h3>
<%
	String p1 = application.____("param1");
	String p2 = application.____("param2");
%>
<p>param1: <%= p1 %><br>param2: <%= p2 %></p>

<h3>실제 파일 경로</h3>
<%= application.____("./4.application.jsp") %>
```

**Answers:** `getServerInfo`, `getInitParameter`, `getInitParameter`, `getRealPath`

**Key point:** `application` scope = server-wide, shared by all users, lives as long as the server runs. Params come from `<context-param>` in `WEB-INF/web.xml`.

---

## Q5 — session scope (5.session.jsp, UserVO.java)

```jsp
<%@page import="sub1.UserVO"%>
<%
	UserVO user = new UserVO(____, ____, ____);
	session.____("user", user);               // 세션에 저장
	UserVO userVO = (UserVO) session.____("user"); // 세션에서 꺼내서 캐스팅
%>
<p>세션 ID: <%= session.____() %></p>
<p>아이디: <%= userVO.getUid() %></p>
```

**Answers:** `"a101"`, `"김유신"`, `23`, `setAttribute`, `getAttribute`, `getId`

**Key point:** `session` scope = per-user, lasts until session timeout/invalidation. Use `setAttribute`/`getAttribute` (cast needed on read).

---

## Q6 — exception handling (6.exception.jsp, web.xml error-page, proc/error.jsp)

Fill in the page directive and web.xml mapping:

```jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	____="errorPage URL" ____="true" %>
```

```xml
<!-- web.xml -->
<error-page>
	<error-code>500</error-code>
	<location>/error/____.jsp</location>
</error-page>
```

**Answers:** `errorPage`, `isErrorPage`, `500` (and 404 maps to `/error/404.jsp`)

**Key point:** In an error page, set `isErrorPage="true"` to access the implicit `exception` object. `web.xml <error-page>` maps HTTP error codes (404, 500) or exception types to error JSPs.

---

## Q7 — Four scopes (7.scopeTest.jsp)

```jsp
<%
	pageContext.____("name", "김유신");    // page scope
	request.____("name", "김춘추");        // request scope
	session.____("name", "장보고");        // session scope
	application.____("name", "강감찬");    // application scope
%>
```

**Answers:** `setAttribute`, `setAttribute`, `setAttribute`, `setAttribute`

**Scope comparison:**

| Scope | Object | Lifetime | Visible to |
|-------|--------|----------|------------|
| page | pageContext | current page only | current JSP |
| request | request | one request (including forward) | forwarded pages too |
| session | session | per user, until timeout/expire | all pages for that user |
| application | application | whole server life | all users, all pages |

---

Save this, fill in the blanks, then check against `ch03/src/main/webapp/` and `WEB-INF/web.xml`.
