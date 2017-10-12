<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="DiazFu.Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="node_modules/mdbootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="node_modules/mdbootstrap/css/mdb.min.css" />
    <link rel="stylesheet" href="node_modules/mdbootstrap/css/style.css" />
</head>
<body>
    <form id="form_inicio_sesion" runat="server" class="p-4">
        <%--ALERTA--%>
        <asp:Literal ID="lAlerta" runat="server">
        </asp:Literal>
        <%--/ALERTA--%>

        <div class="container">
            <div class="row justify-content-md-center align-items-center">

                <div class="col-lg-4">
                    <div id="contenido">
                        <div class="row">
                            <div class="col">
                                <div class="media w-100">
                                    <div class="media-body">
                                        <h5 class="mt-0 mb-1">Iniciar Sesión</h5>
                                    </div>
                                    <img class="d-flex ml-3 align-self-center" src="Images/Logos/light_logo.png" alt="Generic placeholder image" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="md-form">
                                    <asp:TextBox ID="tb_usuario" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                                    <label for="tb_usuario" class="">Usuario</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="md-form">
                                    <asp:TextBox ID="tb_contrasena" runat="server" CssClass="form-control" TextMode="Password" required="required"></asp:TextBox>
                                    <label for="tb_contrasena" class="">Contraseña</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="md-form text-right">
                                    <!-- Default button -->
                                    <asp:Button ID="b_iniciar_sesion" runat="server" Text="Iniciar Sesión" CssClass="btn btn-default" OnClick="b_iniciar_sesion_Click" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <footer class="page-footer center-on-small-only fixed-bottom">
        <%--FOOTER--%>
        <div class="container-fluid">
            <div class="row">
                <div class="col">
                </div>
            </div>
        </div>
        <%--/FOOTER--%>
        <!--Copyright-->
        <div class="footer-copyright">
            <div class="container-fluid">
                © 2017 Copyright | Powered by <a href="mailto:skillcoders42@gmail.com">SkillCoders</a>
            </div>
        </div>
        <!--/.Copyright-->
    </footer>
    <script type="text/javascript" src="node_modules/mdbootstrap/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="node_modules/mdbootstrap/js/popper.min.js"></script>
    <script type="text/javascript" src="node_modules/mdbootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="node_modules/mdbootstrap/js/mdb.min.js"></script>
</body>
</html>
