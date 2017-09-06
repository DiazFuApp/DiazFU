<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Captura.aspx.cs" Inherits="DiazFu.Modules.Administracion.Grupos.Captura" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Cliente</h1>
        <p class="lead">
            Ingresa la información del cliente.
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
            <%--DIRECCION COMPLETA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Direccion" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_Direccion" class="">Dirección Completa</label>
                    </div>
                </div>
            </div>
            <%--TELEFONOS--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_TelefonoCasa" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_TelefonoCasa" class="">Teléfono De Casa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_TelefonoCelular" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_TelefonoCelular" class="">Teléfono Celular</label>
                    </div>
                </div>
            </div>
            <%--CORREO ELECTRONICO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_CorreoElectronico" runat="server" CssClass="form-control" TextMode="Email" required="required"></asp:TextBox>
                        <label for="tb_CorreoElectronico" class="">Correo Electrónico</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_FechaNacimiento" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_FechaNacimiento" class="">Fecha De Nacimiento (DD/MM/YYYY)</label>
                    </div>
                </div>
            </div>
            <%--REDES--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Facebook" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_Facebook" class="">Facebook</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Twitter" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_Twitter" class="">Twitter</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Instagram" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_Instagram" class="">Instagram</label>
                    </div>
                </div>
            </div>
            <%--EMPRESA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_NombreEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_NombreEmpresa" class="">Empresa En La Que Labora</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_PuestoEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_PuestoEmpresa" class="">Puesto En La Empresa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_AntiguedadEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_AntiguedadEmpresa" class="">Antigüedad En La Empresa</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_HorarioEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_HorarioEmpresa" class="">Horario Laboral De La Empresa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_TelefonoEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_TelefonoEmpresa" class="">Teléfono De La Empresa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_SueldoMensualEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_SueldoMensualEmpresa" class="">Sueldo En La Empresa</label>
                    </div>
                </div>
            </div>
            <%--DIRECCION COMPLETA DE LA EMPRESA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_DireccionEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_DireccionEmpresa" class="">Dirección Completa De La Empresa</label>
                    </div>
                </div>
            </div>
            <%--TELEFONO EMPRESA | SUELDO MENSUAL | NOMBRE DEL JEFE--%>
            <div class="row">
                <div class="col-4">
                    <div class="md-form">
                        <asp:TextBox ID="tb_NombreJefeEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_NombreJefeEmpresa" class="">Nombre Del Jefe</label>
                    </div>
                </div>
                <div class="col-4">
                    <div class="md-form">
                        <asp:TextBox ID="tb_TelefonoJefeEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_TelefonoJefeEmpresa" class="">Teléfono Del Jefe</label>
                    </div>
                </div>
            </div>
            <%--DOCUMENTOS--%>
            <div class="row">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <label class="">Foto</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_Foto" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_Foto">Seleccione la foto...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_Foto" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_Foto" target="_blank" runat="server" href="#" download class="btn btn-primary">Foto</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <label class="">Acta De Nacimiento</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_ActaNacimiento" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_ActaNacimiento">Seleccione el acta de nacimiento...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_ActaNacimiento" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_ActaNacimiento" target="_blank" runat="server" href="#" download class="btn btn-primary">Acta De Nacimiento</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <label class="">Constancia De Residencia</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_ConstanciaResidencia" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_ConstanciaResidencia">Seleccione la constancia de residencia...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_ConstanciaResidencia" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_ConstanciaResidencia" target="_blank" runat="server" href="#" download class="btn btn-primary">Constanca De Residencia</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <label class="">CURP</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_CURP" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_CURP">Seleccione la CURP...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_CURP" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_CURP" target="_blank" runat="server" href="#" download class="btn btn-primary">CURP</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <label class="">INE</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_INE" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_INE">Seleccione el INE...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_INE" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_INE" target="_blank" runat="server" href="#" download class="btn btn-primary">INE</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <label class="">Comprobante De Domicilio</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_ComprobanteDomicilio" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_ComprobanteDomicilio">Seleccione el comprobante de domicilio...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_ComprobanteDomicilio" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_ComprobanteDomicilio" target="_blank" runat="server" href="#" download class="btn btn-primary">Comprobante Domicilio</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <div class="row">
                        <div class="col">
                            <label class="">Comprobante De Ingresos</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label class="custom-file w-100">
                                <asp:FileUpload ID="fu_ComprobanteIngresos" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_ComprobanteIngresos">Seleccione el comprobante de ingresos...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_ComprobanteIngresos" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_ComprobanteIngresos" target="_blank" runat="server" href="#" download class="btn btn-primary">Comprobante De Ingresos</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 p-0 text-right">
                <asp:Button ID="b_Crear" runat="server" Text="Crear Cliente" CssClass="btn btn-default" OnClick="b_Crear_Click" />
            </div>
        </div>
    </div>
</asp:Content>
