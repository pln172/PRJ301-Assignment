<%-- 
    Document   : Sell
    Created on : Oct 6, 2021, 11:32:27 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALE MANAGEMENT</title>
        <link rel="icon" href="../img/favicon.png" type="image/png" sizes="16x16">
        <!--        <Link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">-->
        <link href="../css/base_style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/insert_style.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous" defer></script>

        <style>
            footer {
                position: relative;
                bottom: 0;
                width: 100%;
                white-space: nowrap;
                line-height: 60px;
            }
        </style>
        <script>
            function changeurl(url) {
                var host = "${pageContext.request.contextPath}/";
                window.location.href = host + url;
            }
        </script>
    </head>

    <body>
        <header>
            <div class="row">
                <div class="col-md-3">
                    <div class="hleft">
                        <a href="${pageContext.request.contextPath}/emp/sell"><h3>Bán hàng</h3></a>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="hright">
                        <i class="fas fa-history fa-2x" 
                           onclick="changeurl('emp/sell-history')" style="color: white;"></i>
                        <div class="dropdown">
                            <button class="dropbtn">
                                <i class="far fa-user-circle fa-2x" style="color: white;"></i>
                            </button>
                            <div class="dropdown-content">
                                <!--<h5>Tài khoản</h5>-->
                                <h5 onclick="changeurl('logout')">Đăng xuất</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section>
            <div class="container">
                <h4>Đơn hàng bán trong ngày</h4>
                <table class="table table-success table-striped mt-5 mb-5">
                    <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Thời gian</th>
                            <th scope="col">Tổng</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="0"/>
                        <c:forEach items="${requestScope.orders}" var="o">
                            <c:set var="i" value="${i+1}"/>
                            <tr>
                                <th scope="row">${i}</th>
                                <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" 
                                                value = "${o.date}" /></td>
                                <td><fmt:formatNumber type = "number" 
                                                  value = "${o.total}"/> VND</td>
                                <td><a style="text-decoration: underline;color: #0d6efd;cursor: pointer;" 
                                       onclick="detail(${o.id})" data-bs-toggle="modal" data-bs-target="#o${o.id}">Chi tiết</a></td>
                            </tr>
                        <div class="modal fade modal-fullscreen-md-down" id="o${o.id}" tabindex="-1" aria-labelledby="example${o.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="example${o.id}">Khách lẻ</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body" id="modal${o.id}">

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>


                <div id="detail-model">

                </div>
        </section>

        <footer>
            <div class="row">
                <div class="col-md-6">
                    <div class="fleft">
                        <h5>&copy PRJ301</h5>
                        <h5>phuongloan517@gmail.com</h5>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="fright">
                        <i class="fab fa-instagram fa-3x"></i>
                        <i class="fab fa-twitter fa-3x"></i>
                        <i class="fab fa-facebook fa-3x"></i>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
    </script>
    <script>
        function detail(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/emp/sell-history",
                type: "POST",
                datatype: "json",
                data: {
                    id: id
                },
                success: function (result) {
                    var malId = "#modal" + id;
                    var str = `<table class="table">
                    <thead>
                      <tr>
                        <th scope="col">STT</th>
                         <th scope="col">Tên</th>
                          <th scope="col">Số lượng</th>
                          <th scope="col">Giá đơn vị</th>
                           <th scope="col">Tổng</th>
                      </tr>
                    </thead>
                    <tbody>`
                        for (var order in result) {
                            
                        }
                      `<tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                      </tr>
                    </tbody>
                  </table>`;
                    $(malId).html(str);
                }
            });
        }
    </script>
</body>
</html>
