<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Listado.aspx.cs" Inherits="DiazFu.Modules.Prestamos.Pagos.Listado" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Próximos Pagos</h1>
        <p class="lead">
            Monitorea y realiza los próximos pagos en la aplicación.
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
            <asp:gridview id="gvPrestamos" runat="server" autogeneratecolumns="false" showheaderwhenempty="true"
                data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                data-pagination="true" data-page-size="10" data-search="true"
                data-show-toggle="true" data-show-pagination-switch="true" data-show-columns="true"
                data-show-export="true" data-toolbar="#toolbar">
                <Columns>
                    <asp:BoundField DataField="Cliente" HeaderText="Cliente" />
                    <asp:BoundField DataField="TipoPrestamo" HeaderText="Tipo de Préstamo" />
                    <asp:BoundField DataField="MontoAPagar" HeaderText="Monto" DataFormatString="{0:c}" />
                    <asp:BoundField DataField="Plazo" HeaderText="Plazo" />
                    <asp:BoundField DataField="FechaProgramada" HeaderText="Fecha Programada" DataFormatString="{0:d}" />
                </Columns>
            </asp:gridview>
        </div>
    </div>
    <script type="text/javascript">
        document.getElementById("liPagos").className = "actual";
    </script>
</asp:Content>
