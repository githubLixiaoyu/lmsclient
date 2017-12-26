<%
	String fmtLanguage = request.getLocale().getLanguage();
	if ("zh".equals(fmtLanguage.substring(0, 2))) {
		fmtLanguage = "zh_CN";
	} else {
		fmtLanguage = "en_US";
	}
	pageContext.setAttribute("fmtLanguage", fmtLanguage);
%>
<fmt:setLocale value="${fmtLanguage}"/>