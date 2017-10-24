<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Listado.aspx.cs" Inherits="DiazFu.Modules.Actividades.Listado" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Actividades</h1>
        <p class="lead">
            Asigna actividades a los promotores.
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
            <asp:GridView ID="gvActividades" runat="server" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                data-pagination="true" data-page-size="10" data-search="true"
                data-show-toggle="true" data-show-pagination-switch="true" data-show-columns="true"
                data-show-export="true" data-toolbar="#toolbar" OnRowDataBound="gvActividades_RowDataBound">
                <Columns>
                    <asp:BoundField DataField="Promotor" HeaderText="Promotor" />
                    <asp:BoundField DataField="Titulo" HeaderText="Actividad" />
                    <asp:BoundField DataField="Prioridad" HeaderText="Prioridad" />
                    <asp:BoundField DataField="Estatus" HeaderText="Estatus" />
                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Finalizar">
                        <ItemTemplate>
                            <asp:Button ID="bFinalizar" runat="server" CssClass="btn btn-primary" Text="Finalizar" CommandArgument='<%# Bind("Id") %>' OnClick="bFinalizar_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Ver">
                        <ItemTemplate>
                            <a href='<%# "/Modules/Actividades/Captura.aspx?id=" + Eval("Id") %>' class="btn btn-primary">Ver
                            </a>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="col-12 p-0 text-right">
            <a href="Captura.aspx" class="btn btn-default">Crear Actividad</a>
        </div>
    </div>
    <script type="text/javascript">
        document.getElementById("liActividades").className = "actual";
    </script>
</asp:Content>
