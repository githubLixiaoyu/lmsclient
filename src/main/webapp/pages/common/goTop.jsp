<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
        #back-to-top.show {
            opacity: 1;
        }
        #back-to-top {
            position: fixed;
            bottom: 40px;
            right: 40px;
            z-index: 10;
            width: 54px;
            height: 54px;
            text-align: center;
            line-height: 30px;
            cursor: pointer;
            text-decoration: none;
            opacity: 0;
            transition: opacity .2s ease-out;
        }
</style>
<script type="text/javascript">
</script>
</head>
<body>
    <a href="#" id="back-to-top" title="Back to top"><img src="${pageContext.request.contextPath}/images/index/goTop.png"></a>

    <script>
        if ($('#back-to-top').length) {
            var scrollTrigger = 100; // px

            // $(window).scrollTop()与 $(document).scrollTop()产生结果一样
            // 一般使用document注册事件，window使用情况如 scroll, scrollTop, resize
            $(window).on('scroll', function () {
                if ($(window).scrollTop() > scrollTrigger) {
                    $('#back-to-top').addClass('show');
                } else {
                    $('#back-to-top').removeClass('show');
                }
            });

            $('#back-to-top').on('click', function (e) {
                // html,body 都写是为了兼容浏览器
                $('html,body').animate({
                    scrollTop: 0
                }, 700);

                return false;
            });
        }
    </script>
</body>
</html>