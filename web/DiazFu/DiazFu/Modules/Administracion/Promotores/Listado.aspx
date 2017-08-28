<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Listado.aspx.cs" Inherits="DiazFu.Modules.Administracion.Promotores.Listado" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Promotores</h1>
        <p class="lead">
            Crea, edita o elimina los promotores disponibles en la aplicación.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <div id="toolbar">
                <select class="form-control">
                    <option value="">Exportación Básica</option>
                    <option value="all">Exportar Todo</option>
                    <option value="selected">Exportar Página</option>
                </select>
            </div>
            <asp:GridView ID="gvPromotores" runat="server" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                data-pagination="true" data-page-size="10" data-search="true"
                data-show-toggle="true" data-show-pagination-switch="true" data-show-columns="true"
                data-show-export="true" data-toolbar="#toolbar">
                <Columns>
                    <asp:BoundField DataField="Nombre" HeaderText="Nombre" />
                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Editar">
                        <ItemTemplate>
                            <a href='<%# "/Modules/Administracion/Promotores/Captura.aspx?id=" + Eval("Id") %>' class="btn btn-primary">Editar
                            </a>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Eliminar">
                        <ItemTemplate>
                            <asp:Button ID="bEliminar" runat="server" CssClass="btn btn-danger" Text="Eliminar" CommandArgument='<%# Bind("Id") %>' OnClick="bEliminar_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="col-12 p-0 text-right">
            <a href="Captura.aspx" class="btn btn-default">Crear Promotor</a>
        </div>
    </div>
</asp:Content>
