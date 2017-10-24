<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Captura.aspx.cs" Inherits="DiazFu.Modules.Actividades.Captura" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Actividad</h1>
        <p class="lead">
            Ingresa la información de la actividad.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--PROMOTOR--%>
            <div class="row">
                <div class="col">
                    <label for="ddl_Promotor">Promotor</label>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:DropDownList CssClass="form-control" ID="ddl_Promotor" runat="server"></asp:DropDownList>
                    </div>
                </div>
            </div>
            <%--TÍTULO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Titulo" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_Titulo" class="">Título</label>
                    </div>
                </div>
            </div>
            <%--DESCRIPCION--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Descripcion" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_Descripcion" class="">Descripción</label>
                    </div>
                </div>
            </div>
            <%--PROMOTOR--%>
            <div class="row">
                <div class="col">
                    <label for="ddl_Prioridad">Prioridad</label>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:DropDownList CssClass="form-control" ID="ddl_Prioridad" runat="server">
                            <asp:ListItem Selected="True" Text="Baja" Value="1"></asp:ListItem>
                            <asp:ListItem Text="Media" Value="2"></asp:ListItem>
                            <asp:ListItem Text="Alta" Value="3"></asp:ListItem>
                            <asp:ListItem Text="Urgente" Value="4"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                </div>
            </div>
            <div class="col-12 p-0 text-right">
                <asp:Button ID="b_Crear" runat="server" Text="Crear Actividad" CssClass="btn btn-default" OnClick="b_Crear_Click" />
            </div>
            <div class="col-12 p-0 text-right">
                <asp:Button ID="b_Finalizar" runat="server" Text="Finalizar" CssClass="btn btn-default" OnClick="b_Finalizar_Click" Visible="false"/>
            </div>
        </div>
    </div>
    <%--SCRIPTS--%>
    <script type="text/javascript">
        document.getElementById("liAdministracion").className = "actual";
    </script>
    <%--/SCRIPTS--%>
</asp:Content>
