<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Captura.aspx.cs" Inherits="DiazFu.Modules.Administracion.Grupos.Captura" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Grupo</h1>
        <p class="lead">
            Ingresa la información del grupo.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--NOMBRE--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Nombre" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_Nombre" class="">Nombre</label>
                    </div>
                </div>
            </div>
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
            <%--CLIENTES DISPONIBLES--%>
            <asp:UpdatePanel ID="up_Clientes" runat="server">
                <ContentTemplate>

                    <div class="row">
                        <div class="col">
                            <label for="ddl_Clientes">Cliente</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-10">
                            <asp:DropDownList CssClass="form-control" ID="ddl_Clientes" runat="server" AutoPostBack="true"></asp:DropDownList>
                        </div>
                        <div class="col-2 text-center">
                            <asp:Button ID="b_Agregar_Cliente" runat="server" Text="Agregar Cliente" CssClass="btn btn-default" OnClick="b_Agregar_Cliente_Click" formnovalidate />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <asp:GridView ID="gvClientes" runat="server" CssClass="bootstrap-table" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                                data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                                data-pagination="true" data-page-size="10" OnRowDataBound="gvClientes_RowDataBound">
                                <Columns>
                                    <asp:BoundField DataField="Cliente" HeaderText="Nombre" />
                                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Responsable" ControlStyle-CssClass="text-center" ItemStyle-CssClass="text-center">
                                        <ItemTemplate>
                                            <asp:RadioButton ID="rbReponsable" name="responsable" runat="server" GroupName="responsable" OnClick="javascript:SelectSingleRadiobutton(this.id)" />
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Eliminar">
                                        <ItemTemplate>
                                            <asp:Button ID="bEliminar" runat="server" CssClass="btn btn-danger" Text="Eliminar" CommandArgument='<%# Bind("IdCliente") %>' OnClick="bEliminar_Click" formnovalidate />
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>
                </ContentTemplate>
            </asp:UpdatePanel>
            <div class="row">
                <div class="col-12 p-0 text-right">
                    <asp:Button ID="b_Crear" runat="server" Text="Crear Grupo" CssClass="btn btn-default" OnClick="b_Crear_Click" />
                </div>
            </div>
        </div>
    </div>
    <script language="javascript" type="text/javascript">
        function SelectSingleRadiobutton(rdbtnid) {
            var rdBtn = document.getElementById(rdbtnid);
            var rdBtnList = document.getElementsByTagName("input");
            for (i = 0; i < rdBtnList.length; i++) {
                if (rdBtnList[i].type == "radio" && rdBtnList[i].id != rdBtn.id) {
                    rdBtnList[i].checked = false;
                }
            }
        }
    </script>
</asp:Content>
