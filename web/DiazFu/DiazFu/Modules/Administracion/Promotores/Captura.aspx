<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Captura.aspx.cs" Inherits="DiazFu.Modules.Administracion.Promotores.Captura" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Promotor</h1>
        <p class="lead">
            Ingresa la información del promotor.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--NOMBRE - RFC--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Nombre" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_Nombre" class="">Nombre</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_RFC" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_RFC" class="">RFC</label>
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
            <%--FECHA DE NACIMIENTO | CURP | CLAVE DE ELECTOR--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_FechaNacimiento" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_FechaNacimiento" class="">Fecha De Nacimiento (DD/MM/YYYY)</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_CURP" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_CURP" class="">CURP</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_ClaveElector" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_ClaveElector" class="">Clave De Elector</label>
                    </div>
                </div>
            </div>
            <%--USUARIO | CONTRASEÑAS--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Usuario" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_Usuario" class="">Usuario</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Contrasena" runat="server" CssClass="form-control" required="required" TextMode="Password"></asp:TextBox>
                        <label for="tb_Contrasena" class="">Contraseña</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_ConfirmarContrasena" runat="server" CssClass="form-control" required="required" TextMode="Password"></asp:TextBox>
                        <label for="tb_ConfirmarContrasena" class="">Confirmar Contraseña</label>
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
        </div>
    </div>
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Primer Referencia</h1>
        <p class="lead">
            Ingresa la información de la primer referencia.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--NOMBRE - RFC--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_Nombre" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_pr_Nombre" class="">Nombre</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_RFC" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_pr_RFC" class="">RFC</label>
                    </div>
                </div>
            </div>
            <%--DIRECCION COMPLETA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_Direccion" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_pr_Direccion" class="">Dirección Completa</label>
                    </div>
                </div>
            </div>
            <%--TELEFONOS--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_TelefonoCasa" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_pr_TelefonoCasa" class="">Teléfono De Casa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_TelefonoCelular" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_pr_TelefonoCelular" class="">Teléfono Celular</label>
                    </div>
                </div>
            </div>
            <%--CORREO ELECTRONICO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_CorreoElectronico" runat="server" CssClass="form-control" TextMode="Email" required="required"></asp:TextBox>
                        <label for="tb_pr_CorreoElectronico" class="">Correo Electrónico</label>
                    </div>
                </div>
            </div>
            <%--REDES--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_Facebook" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_pr_Facebook" class="">Facebook</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_Twitter" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_pr_Twitter" class="">Twitter</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_Instagram" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_pr_Instagram" class="">Instagram</label>
                    </div>
                </div>
            </div>
            <%--FECHA DE NACIMIENTO | CURP | CLAVE DE ELECTOR--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_FechaNacimiento" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_pr_FechaNacimiento" class="">Fecha De Nacimiento (DD/MM/YYYY)</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_CURP" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_pr_CURP" class="">CURP</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_ClaveElector" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_pr_ClaveElector" class="">Clave De Elector</label>
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
                                <asp:FileUpload ID="fu_pr_Foto" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_pr_Foto">Seleccione la foto...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_pr_Foto" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_pr_Foto" target="_blank" runat="server" href="#" download class="btn btn-primary">Foto</a>
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
                                <asp:FileUpload ID="fu_pr_ActaNacimiento" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_pr_ActaNacimiento">Seleccione el acta de nacimiento...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_pr_ActaNacimiento" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_pr_ActaNacimiento" target="_blank" runat="server" href="#" download class="btn btn-primary">Acta De Nacimiento</a>
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
                                <asp:FileUpload ID="fu_pr_ConstanciaResidencia" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_pr_ConstanciaResidencia">Seleccione la constancia de residencia...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_pr_ConstanciaResidencia" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_pr_ConstanciaResidencia" target="_blank" runat="server" href="#" download class="btn btn-primary">Constanca De Residencia</a>
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
                                <asp:FileUpload ID="fu_pr_CURP" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_pr_CURP">Seleccione la CURP...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_pr_CURP" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_pr_CURP" target="_blank" runat="server" href="#" download class="btn btn-primary">CURP</a>
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
                                <asp:FileUpload ID="fu_pr_INE" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_pr_INE">Seleccione el INE...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_pr_INE" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_pr_INE" target="_blank" runat="server" href="#" download class="btn btn-primary">INE</a>
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
                                <asp:FileUpload ID="fu_pr_ComprobanteDomicilio" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_pr_ComprobanteDomicilio">Seleccione el comprobante de domicilio...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_pr_ComprobanteDomicilio" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_pr_ComprobanteDomicilio" target="_blank" runat="server" href="#" download class="btn btn-primary">Comprobante De Domicilio</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="jumbotron mt-4">
        <h1 class="h1-responsive">Segunda Referencia</h1>
        <p class="lead">
            Ingresa la información de la segunda referencia.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--NOMBRE - RFC--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_Nombre" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_sr_Nombre" class="">Nombre</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_RFC" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_RFC" class="">RFC</label>
                    </div>
                </div>
            </div>
            <%--DIRECCION COMPLETA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_Direccion" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_sr_Direccion" class="">Dirección Completa</label>
                    </div>
                </div>
            </div>
            <%--TELEFONOS--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_TelefonoCasa" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_sr_TelefonoCasa" class="">Teléfono De Casa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_TelefonoCelular" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_sr_TelefonoCelular" class="">Teléfono Celular</label>
                    </div>
                </div>
            </div>
            <%--CORREO ELECTRONICO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_CorreoElectronico" runat="server" CssClass="form-control" TextMode="Email" required="required"></asp:TextBox>
                        <label for="tb_sr_CorreoElectronico" class="">Correo Electrónico</label>
                    </div>
                </div>
            </div>
            <%--REDES--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_Facebook" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_Facebook" class="">Facebook</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_Twitter" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_Twitter" class="">Twitter</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_Instagram" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_Instagram" class="">Instagram</label>
                    </div>
                </div>
            </div>
            <%--FECHA DE NACIMIENTO | CURP | CLAVE DE ELECTOR--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_FechaNacimiento" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_sr_FechaNacimiento" class="">Fecha De Nacimiento (DD/MM/YYYY)</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_CURP" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_CURP" class="">CURP</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_ClaveElector" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_sr_ClaveElector" class="">Clave De Elector</label>
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
                                <asp:FileUpload ID="fu_sr_Foto" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_sr_Foto">Seleccione la foto...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_sr_Foto" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_sr_Foto" target="_blank" runat="server" href="#" download class="btn btn-primary">Foto</a>
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
                                <asp:FileUpload ID="fu_sr_ActaNacimiento" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_sr_ActaNacimiento">Seleccione el acta de nacimiento...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_sr_ActaNacimiento" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_sr_ActaNacimiento" target="_blank" runat="server" href="#" download class="btn btn-primary">Acta De Nacimiento</a>
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
                                <asp:FileUpload ID="fu_sr_ConstanciaResidencia" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_sr_ConstanciaResidencia">Seleccione la constancia de residencia...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_sr_ConstanciaResidencia" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_sr_ConstanciaResidencia" target="_blank" runat="server" href="#" download class="btn btn-primary">Constancia De Residencia</a>
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
                                <asp:FileUpload ID="fu_sr_CURP" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_sr_CURP">Seleccione la CURP...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_sr_CURP" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_sr_CURP" target="_blank" runat="server" href="#" download class="btn btn-primary">CURP</a>
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
                                <asp:FileUpload ID="fu_sr_INE" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_sr_INE">Seleccione el INE...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_sr_INE" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_sr_INE" target="_blank" runat="server" href="#" download class="btn btn-primary">INE</a>
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
                                <asp:FileUpload ID="fu_sr_ComprobanteDomicilio" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_sr_ComprobanteDomicilio">Seleccione el comprobante de domicilio...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_sr_ComprobanteDomicilio" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_sr_ComprobanteDomicilio" target="_blank" runat="server" href="#" download class="btn btn-primary">Comprobante De Domicilio</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 p-0 text-right">
                <asp:Button ID="b_Crear" runat="server" Text="Crear Promotor" CssClass="btn btn-default" OnClick="b_Crear_Click" />
            </div>
        </div>
    </div>

    <%--SCRIPTS--%>
    <script type="text/javascript">
        function file(control) {
            var filename = $('#' + control.id).val();
            $('#' + control.parentElement.lastElementChild.id).html(filename);
        }
    </script>
    <%--/SCRIPTS--%>
</asp:Content>
