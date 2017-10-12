﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Listado.aspx.cs" Inherits="DiazFu.Modules.Prestamos.PrestamosIndividuales.Listado" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Préstamos Individuales</h1>
        <p class="lead">
            Crea y monitorea los préstamos individuales disponibles en la aplicación.
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
            <asp:GridView ID="gvPrestamos" runat="server" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                data-pagination="true" data-page-size="10" data-search="true"
                data-show-toggle="true" data-show-pagination-switch="true" data-show-columns="true"
                data-show-export="true" data-toolbar="#toolbar" OnRowDataBound="gvPrestamos_RowDataBound">
                <Columns>
                    <asp:BoundField DataField="Cliente" HeaderText="Cliente" />
                    <asp:TemplateField HeaderStyle-Width="40" HeaderText="Autorizar">
                        <ItemTemplate>
                            <a id="aAutorizar" runat="server" href='<%# "/Modules/Prestamos/PrestamosIndividuales/Captura.aspx?id=" + Eval("Id") %>' class="btn btn-primary">Autorizar
                            </a>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="col-12 p-0 text-right">
            <a href="Captura.aspx" class="btn btn-default">Solicitar Préstamo Individual</a>
        </div>
    </div>
    <script type="text/javascript">
        document.getElementById("liPrestamos").className = "actual";
    </script>
</asp:Content>
