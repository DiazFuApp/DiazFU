<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Pagos.aspx.cs" Inherits="DiazFu.Modules.Prestamos.PrestamosGrupales.Pagos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Pagos Grupales</h1>
        <p class="lead">
            Asigna los pagos a los clientes de este préstamo grupal.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <asp:Repeater ID="r_Clientes" runat="server">
                <ItemTemplate>
                    <div class="col-6 p-0 mb-5">
                        <asp:HiddenField ID="hf_IdCliente" runat="server" Value="<%# Eval("Id") %>"/>
                        <div class="col">
                            <h3 class="h3-responsive"><%# Eval("Cliente") %></h3>
                        </div>
                        <div class="col">
                            <asp:TextBox ID="tb_Monto" runat="server" Text="$0.00"></asp:TextBox>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>
        <div class="col-12 p-0 text-right">
            <asp:Button ID="bPagar" runat="server" Text="Asignar Pago" CssClass="btn btn-default" OnClick="bPagar_Click" />
        </div>
    </div>
    <script type="text/javascript">
        document.getElementById("liPrestamos").className = "actual";
    </script>
</asp:Content>
