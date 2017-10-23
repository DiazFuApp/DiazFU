<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Pagos.aspx.cs" Inherits="DiazFu.Modules.Prestamos.PrestamosIndividuales.Pagos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Pagos Individuales</h1>
        <p class="lead">
            Asigna los pagos al cliente de este préstamo individual.
        </p>
        <hr class="my-2">
        <div class="row">
            <div class="col">
                <label for="ddl_TipoPago">Tipo de Pago</label>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <asp:DropDownList CssClass="form-control" ID="ddl_TipoPago" runat="server" required>
                    <asp:ListItem Selected="True" Text="Efectivo" Value="Efectivo"></asp:ListItem>
                    <asp:ListItem Text="Depósito" Value="Depósito"></asp:ListItem>
                    <asp:ListItem Text="Cheque" Value="Cheque"></asp:ListItem>
                </asp:DropDownList>
            </div>
        </div>
        <div class="row">
            <div class="col-12 p-0">
                <div class="col p-0 mb-5">
                    <div class="col">
                        <h3 class="h3-responsive">
                            <asp:Label ID="l_Cliente" runat="server" Text="Label"></asp:Label>
                        </h3>
                    </div>
                    <div class="col">
                        <asp:TextBox ID="tb_Monto" runat="server" Text="$0.00"></asp:TextBox>
                    </div>
                    <div class="col">
                        <span>Plazo Actual: </span>
                        <asp:Label ID="l_Plazo" runat="server" Text=""></asp:Label>
                    </div>
                    <div class="col">
                        <span>Monto Restante [Sin Morosidad]: </span>
                        <asp:Label ID="l_Restante" runat="server" Text="$0.00"></asp:Label>
                    </div>
                    <div class="col">
                        <asp:GridView ID="gv_PagosCliente" runat="server" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                            data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                            data-pagination="true" data-page-size="10">
                            <Columns>
                                <asp:BoundField DataField="MontoAPagar" HeaderText="Monto A Pagar" DataFormatString="{0:c}" />
                                <asp:BoundField DataField="MontoPagado" HeaderText="Monto Pagado" DataFormatString="{0:c}" />
                                <asp:BoundField DataField="Plazo" HeaderText="Plazo" />
                                <asp:BoundField DataField="TipoPago" HeaderText="Tipo De Pago" />
                                <asp:BoundField DataField="FechaProgramada" HeaderText="Fecha Programada" DataFormatString="{0:d}" />
                                <asp:BoundField DataField="FechaPago" HeaderText="Fecha De Pago" DataFormatString="{0:d}" />
                                <asp:BoundField DataField="Estatus" HeaderText="Estatus" />
                            </Columns>
                        </asp:GridView>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 p-0 text-right">
                <asp:Button ID="bPagar" runat="server" Text="Asignar Pago" CssClass="btn btn-default" OnClick="bPagar_Click" />
            </div>
        </div>
    </div>
    <script type="text/javascript">
        document.getElementById("liPrestamos").className = "actual";
    </script>
</asp:Content>
