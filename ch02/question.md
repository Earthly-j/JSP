
# JSP Chapter 2 Quiz — Fill in the Blanks

Test your knowledge of the four JSP files in `src/main/webapp/`. Write the missing code where you see `____`.

---

## Q1 — 1.Scriptlet.jsp

**Scriptlet** (`<% %>`): raw Java code.
**Expression** (`<%= %>`): output a value into HTML.

Fill in the missing declarations and output lines:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1.Scriptlet</title>
</head>
<body>
	<h3>1. 스크립트 요소</h3>

	<h4>스크립트릿(Scriptlet)</h4>
	<%
		____ var1 = 1;
		____ var2 = true;
		____ var3 = 3.14;
		____ var4 = "Hello";

		out.println("<p>var1 :" + var1 + "</p>");
		out.println(____);
	%>
	<h4>표현식(Expression)</h4>
	<p><%= ____ %></p>
	<p><%= ____ %></p>
</body>
</html>
```

**Answers:** int, boolean, double, String; `"<p>var2 :" + var2 + "</p>"`, var3, var4

---

## Q2 — 2.Condition.jsp

Fill in the conditions and HTML output blocks:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2. Condition</title>
</head>
<body>
	<h3>2. 조건문</h3>

	<h4>if</h4>
	<%
		int num1 = 1;
		if(____) {
			out.println("<p>num1은 0보다 크다.</p>");
		}
	%>

	<h4>if ~ else</h4>
	<%
		int var1 = 1, var2 = 2;
		if(____) {
	%>
		<p>var1은 var2보다 크다.</p>
	<%
		} else {
	%>
		<p>var1은 var2보다 작다.</p>
	<%
		}
	%>

	<h4>if ~ else if ~ else</h4>
	<% if(____) { %>
		<p>num1은 num2 보다 크다.</p>
	<% } else if(____) { %>
		<p>num2은 num3 보다 크다.</p>
	<% } else if(____) { %>
		<p>num3은 num4 보다 크다.</p>
	<% } else { %>
		<p>num4가 가장 크다.</p>
	<% } %>
</body>
</html>
```

**Answers:** `num1 > 0`, `var1 > var2`, `num1 > num2`, `num2 > num3`, `num3 > num4`

---

## Q3 — 3.Loop.jsp

Fill in the loop headers:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3. Loop</title>
</head>
<body>
	<h3>3. 반복문</h3>
	<h4>for</h4>
	<%
		for(____) {
			out.println("<p>i : " + i + "</p>");
		}
	%>

	<%
		for(____) {
	%>
		<p>j : <%= j %></p>
	<%
		}
	%>

	<h4>while</h4>
	<%
		int k = 1;
		while(____) {
	%>
		<p>k: <%= k %></p>
	<%
			k++;
		}
	%>

	<h4>구구단</h4>
	<table border="1">
		<tr>
			<th>2단</th><th>3단</th><th>4단</th><th>5단</th>
			<th>6단</th><th>7단</th><th>8단</th><th>9단</th>
		</tr>
		<% for(____) { %>
		<tr>
			<% for(____) { %>
			<td><%= y %> x <%= x %> = <%= x*y %></td>
			<% } %>
		</tr>
		<% } %>
	</table>
</body>
</html>
```

**Answers:** `int i=1; i<=3; i++`, `int j=1; j<=3; j++`, `k <= 3`, `int x = 1; x <= 9; x++`, `int y = 2; y <= 9; y++`

---

## Q4 — 4.Directive.jsp + Account.java

Fill in the directives and Java usage:

**Account.java:**

```java
package sub1;

public class Account {
	private String bank;
	private String accId;
	private String name;
	private int balance;

	public Account(____) {
		super();
		this.bank = bank;
		this.accId = accId;
		this.name = name;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [bank=" + bank + ", accId=" + accId + ", name=" + name + ", balance=" + balance + "]";
	}
}
```

**4.Directive.jsp:**

```jsp
<%@page import="____" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4. Directive</title>
</head>
<body>
	<h3>4. 지시자</h3>

	<h4>page 지시자</h4>
	<%
		Account kb = new Account(____, ____, ____, ____);
		Account wr = new Account(____, ____, ____, ____);
	%>
	<p><%= kb %></p>
	<p><%= wr %></p>

	<h4>include 지시자</h4>
	<%@ include file="____" %>
	<%@ include file="____" %>
</body>
</html>
```

**Answers:**
- Account constructor: `String bank, String accId, String name, int balance`
- Import: `sub1.Account`
- kb args: `"국민은행"`, `"101-12-1001"`, `"김유신"`, `10000`
- wr args: `"우리은행"`, `"101-12-1002"`, `"김춘추"`, `10000`
- includes: `"./inc/header.jsp"`, `"./inc/footer.jsp"`

---

## Q5 — Short Answer

1. What is the difference between `<% %>` and `<%= %>`?
2. What does the `page` directive with `import="sub1.Account"` do?
3. What does the `include` directive do vs. writing HTML directly?
4. In 3.Loop.jsp, what is printed by the 구구단 section?

---

Save your answers, then compare with the originals in `src/main/webapp/` to check.
