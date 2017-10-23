<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="Captura.aspx.cs" Inherits="DiazFu.Modules.Prestamos.PrestamosGrupales.Captura" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphContenido" runat="server">
    <%--DATOS DEL PRESTAMO--%>
    <div class="jumbotron mt-4" id="jtDatosPrestamo" runat="server">
        <h1 class="h1-responsive">Préstamo Grupal</h1>
        <p class="lead">
            Ingresa la información del préstamo del grupo para su autorización.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--GRUPO | MOTIVO--%>
            <div class="row">
                <div class="col">
                    <label for="ddl_Grupo">Grupo</label>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <asp:DropDownList CssClass="form-control" ID="ddl_Grupo" runat="server" required AutoPostBack="true" OnSelectedIndexChanged="ddl_Grupo_SelectedIndexChanged"></asp:DropDownList>
                </div>
            </div>
            <%--MOTIVO | CANTIDAD SOLICITADA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Motivo" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_Motivo" class="">Motivo del préstamo</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_CantidadSolicitada" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_CantidadSolicitada" class="">Cantidad A Solicitar</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Garantia" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_Garantia" class="">Garantía</label>
                    </div>
                </div>
            </div>
            <%--OBSERVACIONES --%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Observaciones" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_Observaciones" class="">Observaciones</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--DOCUMENTOS--%>
    <div class="jumbotron mt-4" id="jtDocumentos" runat="server">
        <h1 class="h1-responsive">Documentos</h1>
        <p class="lead">
            Valida los documentos del grupo para este préstamo.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <asp:Repeater ID="r_Documentos" runat="server" OnItemDataBound="r_Documentos_ItemDataBound">
                <ItemTemplate>
                    <div class="col-12 p-0 mb-5">
                        <h3 class="h3-responsive"><%# Eval("Cliente") %></h3>
                        <%--DOCUMENTOS--%>
                        <asp:GridView ID="gvDocumentos" runat="server" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                            data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true">
                            <Columns>
                                <asp:BoundField DataField="TipoDocumento" HeaderText="Tipo de Documento" />
                                <asp:TemplateField HeaderText="Editar">
                                    <ItemTemplate>
                                        <a id="a_Archivo" target="_blank" runat="server" href='<%# Eval("URLDocumento") %>' download class="btn btn-primary">DESCARGAR</a>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>
    </div>
    <%--AVAL--%>
    <div class="jumbotron mt-4" id="jtAval" runat="server">
        <h1 class="h1-responsive">Aval</h1>
        <p class="lead">
            Ingresa la información del aval para este préstamo.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--NOMBRE | RFC --%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_Nombre" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_aval_Nombre" class="">Nombre</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_RFC" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_RFC" class="">RFC</label>
                    </div>
                </div>
            </div>
            <%--DIRECCIÓN --%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_Direccion" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_aval_Direccion" class="">Dirección</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_DireccionReferencia" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_DireccionReferencia" class="">Referencia De Dirección</label>
                    </div>
                </div>
            </div>
            <%--TELEFONO CASA | TELEFONO PERSONAL--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_TelefonoCasa" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_aval_TelefonoCasa" class="">Teléfono De Casa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_TelefonoCelular" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_aval_TelefonoCelular" class="">Teléfono Celular</label>
                    </div>
                </div>
            </div>
            <%--CORREO ELECTRONICO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_CorreoElectronico" runat="server" CssClass="form-control" TextMode="Email" required="required"></asp:TextBox>
                        <label for="tb_aval_CorreoElectronico" class="">Correo Electrónico</label>
                    </div>
                </div>
            </div>
            <%--REDES--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_Facebook" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_Facebook" class="">Facebook</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_Twitter" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_Twitter" class="">Twitter</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_Instagram" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_Instagram" class="">Instagram</label>
                    </div>
                </div>
            </div>
            <%--FECHA DE NACIMIENTO | CURP --%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_FechaNacimiento" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_aval_FechaNacimiento" class="">Fecha De Nacimiento (DD/MM/YYYY)</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_CURP" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_CURP" class="">CURP</label>
                    </div>
                </div>
            </div>
            <%--PARENTESCO | CLAVE DE ELECTOR--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_Parentesco" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_aval_Parentesco" class="">Parentesco</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_ClaveElector" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        <label for="tb_aval_ClaveElector" class="">Clave De Elector</label>
                    </div>
                </div>
            </div>
            <%--EMPRESA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_NombreEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_NombreEmpresa" class="">Empresa En La Que Labora</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_PuestoEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_PuestoEmpresa" class="">Puesto En La Empresa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_AntiguedadEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_AntiguedadEmpresa" class="">Antigüedad En La Empresa</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_TelefonoEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_TelefonoEmpresa" class="">Teléfono De La Empresa</label>
                    </div>
                </div>
                <div class="col-4">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_NombreJefeEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_NombreJefeEmpresa" class="">Nombre Del Jefe</label>
                    </div>
                </div>
            </div>
            <%--DIRECCION COMPLETA DE LA EMPRESA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_aval_DireccionEmpresa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_aval_DireccionEmpresa" class="">Dirección Completa De La Empresa</label>
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
                                <asp:FileUpload ID="fu_aval_Foto" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_aval_Foto">Seleccione la foto...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_aval_Foto" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_aval_Foto" target="_blank" runat="server" href="#" download class="btn btn-primary">Foto</a>
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
                                <asp:FileUpload ID="fu_aval_ActaNacimiento" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_aval_ActaNacimiento">Seleccione el acta de nacimiento...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_aval_ActaNacimiento" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_aval_ActaNacimiento" target="_blank" runat="server" href="#" download class="btn btn-primary">Acta De Nacimiento</a>
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
                                <asp:FileUpload ID="fu_aval_ConstanciaResidencia" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_aval_ConstanciaResidencia">Seleccione la constancia de residencia...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_aval_ConstanciaResidencia" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_aval_ConstanciaResidencia" target="_blank" runat="server" href="#" download class="btn btn-primary">Constanca De Residencia</a>
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
                                <asp:FileUpload ID="fu_aval_CURP" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_aval_CURP">Seleccione la CURP...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_aval_CURP" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_aval_CURP" target="_blank" runat="server" href="#" download class="btn btn-primary">CURP</a>
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
                                <asp:FileUpload ID="fu_aval_INE" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_aval_INE">Seleccione el INE...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_aval_INE" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_aval_INE" target="_blank" runat="server" href="#" download class="btn btn-primary">INE</a>
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
                                <asp:FileUpload ID="fu_aval_ComprobanteDomicilio" runat="server" class="custom-file-input" onchange="file(this);" required="required" />
                                <span class="custom-file-control" id="span_file_aval_ComprobanteDomicilio">Seleccione el comprobante de domicilio...</span>
                            </label>
                        </div>
                    </div>
                    <div id="div_aval_ComprobanteDomicilio" runat="server" visible="false" class="row">
                        <div class="col">
                            <a id="a_aval_ComprobanteDomicilio" target="_blank" runat="server" href="#" download class="btn btn-primary">Comprobante Domicilio</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--REFERENCIA 1--%>
    <div class="jumbotron mt-4" id="jtPrimerReferencia" runat="server">
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
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_pr_DireccionReferencia" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_pr_DireccionReferencia" class="">Referencia De Dirección</label>
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
    <%--REFERENCIA 2--%>
    <div class="jumbotron mt-4" id="jtSegundaReferencia" runat="server">
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
                        <asp:TextBox ID="tb_sr_Nombre" runat="server" CssClass="form-control"></asp:TextBox>
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
            <%--DIRECCION COMPLETA | REFERENCIA--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_Direccion" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_Direccion" class="">Dirección Completa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_DireccionReferencia" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_DireccionReferencia" class="">Referencia De Dirección</label>
                    </div>
                </div>
            </div>
            <%--TELEFONOS--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_TelefonoCasa" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_TelefonoCasa" class="">Teléfono De Casa</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_TelefonoCelular" runat="server" CssClass="form-control"></asp:TextBox>
                        <label for="tb_sr_TelefonoCelular" class="">Teléfono Celular</label>
                    </div>
                </div>
            </div>
            <%--CORREO ELECTRONICO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_sr_CorreoElectronico" runat="server" CssClass="form-control" TextMode="Email"></asp:TextBox>
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
                        <asp:TextBox ID="tb_sr_FechaNacimiento" runat="server" CssClass="form-control"></asp:TextBox>
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
                        <asp:TextBox ID="tb_sr_ClaveElector" runat="server" CssClass="form-control"></asp:TextBox>
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
                                <asp:FileUpload ID="fu_sr_Foto" runat="server" class="custom-file-input" onchange="file(this);" />
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
                                <asp:FileUpload ID="fu_sr_ActaNacimiento" runat="server" class="custom-file-input" onchange="file(this);" />
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
                                <asp:FileUpload ID="fu_sr_ConstanciaResidencia" runat="server" class="custom-file-input" onchange="file(this);" />
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
                                <asp:FileUpload ID="fu_sr_CURP" runat="server" class="custom-file-input" onchange="file(this);" />
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
                                <asp:FileUpload ID="fu_sr_INE" runat="server" class="custom-file-input" onchange="file(this);" />
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
                                <asp:FileUpload ID="fu_sr_ComprobanteDomicilio" runat="server" class="custom-file-input" onchange="file(this);" />
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
                <asp:Button ID="b_Crear" runat="server" Text="Solicitar Préstamo" CssClass="btn btn-default" OnClick="b_Crear_Click" />
            </div>
        </div>
    </div>
    <%--AUTORIZACIÓN DEL PRESTAMO--%>
    <div class="jumbotron mt-4" id="jtAutorizacion" runat="server" visible="false">
        <h1 class="h1-responsive">Autorización</h1>
        <p class="lead">
            Ingresa los datos finales para autorizar este préstamo.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--CANTIDAD A OTORGAR--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_CantidadAOtorgar" runat="server" CssClass="form-control" required></asp:TextBox>
                        <label for="tb_CantidadAOtorgar" class="">Cantidad A Otorgar</label>
                    </div>
                </div>
            </div>
            <%--PLAZOS--%>
            <div class="row">
                <div class="col">
                    <label for="ddl_Plazos">Plazos</label>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <asp:DropDownList CssClass="form-control" ID="ddl_Plazos" runat="server">
                        <asp:ListItem Selected="True" Text="8 semanas" Value="8"></asp:ListItem>
                        <asp:ListItem Text="12 semanas" Value="12"></asp:ListItem>
                        <asp:ListItem Text="16 semanas" Value="16"></asp:ListItem>
                    </asp:DropDownList>
                </div>
            </div>
        </div>
        <div class="col-12 p-0 text-right">
            <asp:Button ID="bAutorizar" runat="server" Text="Autorizar Préstamo" CssClass="btn btn-default" OnClick="bAutorizar_Click" />
        </div>
    </div>
    <%--ENTREGA DEL PRESTAMO--%>
    <div class="jumbotron mt-4" id="jEntrega" runat="server" visible="false">
        <h1 class="h1-responsive">Entrega de Préstamo</h1>
        <p class="lead">
            Finaliza la entrega del préstamo y confirma las fechas de pago.
        </p>
        <hr class="my-2">
        <div class="col-12 p-0">
            <%--ANTICIPO--%>
            <div class="row">
                <div class="col">
                    <div class="md-form">
                        <asp:TextBox ID="tb_Anticipo" runat="server" CssClass="form-control" Enabled="false"></asp:TextBox>
                        <label for="tb_Anticipo" class="">Anticipo</label>
                    </div>
                </div>
            </div>
            <%--PAGOS--%>
            <div class="row">
                <div class="col">
                    <asp:GridView ID="gvPagos" runat="server" AutoGenerateColumns="false" ShowHeaderWhenEmpty="true"
                        data-toggle="table" data-classes="table table-hover table-no-bordered" data-striped="true"
                        data-pagination="true" data-page-size="10" data-toolbar="#toolbar" OnRowDataBound="gvPagos_RowDataBound">
                        <Columns>
                            <asp:BoundField DataField="Cliente" HeaderText="Integrante" />
                            <asp:BoundField DataField="Plazo" HeaderText="Plazo" />
                            <asp:BoundField DataField="MontoAPagar" HeaderText="Monto" DataFormatString="{0:c}" />
                            <asp:BoundField DataField="FechaProgramada" HeaderText="Fecha de Pago" DataFormatString="{0:d}" />
                            <asp:BoundField DataField="Estatus" HeaderText="Estatus" />
                        </Columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
        <div class="col-12 p-0 text-right">
            <asp:Button ID="bEntregar" runat="server" Text="Entregar Préstamo" CssClass="btn btn-default" OnClick="bEntregar_Click" />
        </div>
    </div>

    <%--SCRIPTS--%>
    <script type="text/javascript">
        function file(control) {
            var filename = $('#' + control.id).val();
            $('#' + control.parentElement.lastElementChild.id).html(filename);
        }
        document.getElementById("liPrestamos").className = "actual";
    </script>
    <%--/SCRIPTS--%>
</asp:Content>
