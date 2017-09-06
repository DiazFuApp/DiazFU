using DiazFu.App_Code.Entidades;
using DiazFu.App_Code.Utilerias;
using System;
using System.Web.UI.WebControls;

namespace DiazFu.Modules.Administracion.Clientes
{
    public partial class Captura : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["id"] != null)
                {
                    ConsultarCliente();
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA CONSULTAR UN CLIENTE A EDITAR
        /// </summary>
        private void ConsultarCliente()
        {
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Clientes Cliente = new App_Code.Entidades.Clientes
            {
                Id = ID
            };
            Cliente.ConsultarID();

            //LLENAR FORMULARIO
            tb_Nombre.Text = Cliente.Nombre;
            tb_Direccion.Text = Cliente.Direccion;
            tb_TelefonoCasa.Text = Cliente.TelefonoCasa;
            tb_TelefonoCelular.Text = Cliente.TelefonoCelular;
            tb_CorreoElectronico.Text = Cliente.CorreoElectronico;
            tb_FechaNacimiento.Text = Cliente.FechaNacimiento.ToString();
            div_Foto.Visible = true;
            a_Foto.HRef = Cliente.URLFoto;
            fu_Foto.Attributes.Remove("required");
            tb_NombreEmpresa.Text = Cliente.NombreEmpresa;
            tb_PuestoEmpresa.Text = Cliente.PuestoEmpresa;
            tb_DireccionEmpresa.Text = Cliente.DireccionEmpresa;
            tb_HorarioEmpresa.Text = Cliente.HorarioEmpresa;
            tb_AntiguedadEmpresa.Text = Cliente.Antiguedad;
            tb_TelefonoEmpresa.Text = Cliente.TelefonoEmpresa;
            tb_SueldoMensualEmpresa.Text = Cliente.SueldoMensual;
            tb_NombreJefeEmpresa.Text = Cliente.NombreJefe;
            tb_TelefonoJefeEmpresa.Text = Cliente.TelefonoJefe;
            //-------------------------------------------------------------------------------------------------
            //REDES
            RedesSociales ClienteFacebook = new RedesSociales
            {
                IdActor = Cliente.Id,
                IdTipoActor = 3,
                IdTipoRedSocial = 1
            };
            ClienteFacebook.ConsultarID();
            tb_Facebook.Text = ClienteFacebook.URL;
            RedesSociales ClienteTwitter = new RedesSociales
            {
                IdActor = Cliente.Id,
                IdTipoActor = 3,
                IdTipoRedSocial = 2
            };
            ClienteTwitter.ConsultarID();
            tb_Twitter.Text = ClienteTwitter.URL;
            RedesSociales ClienteInstagram = new RedesSociales
            {
                IdActor = Cliente.Id,
                IdTipoActor = 3,
                IdTipoRedSocial = 2
            };
            ClienteInstagram.ConsultarID();
            tb_Instagram.Text = ClienteInstagram.URL;
            //-------------------------------------------------------------------------------------------------
            //DOCUMENTOS
            Documentos ClienteActaNacimiento = new Documentos
            {
                IdTipoActor = 3,
                IdActor = Cliente.Id,
                IdTipoDocumento = 1
            };
            ClienteActaNacimiento.ConsultarID();
            a_ActaNacimiento.HRef = ClienteActaNacimiento.URLDocumento;
            div_ActaNacimiento.Visible = true;
            fu_ActaNacimiento.Attributes.Remove("required");
            Documentos ClienteConstanciaResidencia = new Documentos
            {
                IdTipoActor = 3,
                IdActor = Cliente.Id,
                IdTipoDocumento = 4
            };
            ClienteConstanciaResidencia.ConsultarID();
            a_ConstanciaResidencia.HRef = ClienteConstanciaResidencia.URLDocumento;
            div_ConstanciaResidencia.Visible = true;
            fu_ConstanciaResidencia.Attributes.Remove("required");
            Documentos ClienteCURP = new Documentos
            {
                IdTipoActor = 3,
                IdActor = Cliente.Id,
                IdTipoDocumento = 3
            };
            ClienteCURP.ConsultarID();
            a_CURP.HRef = ClienteCURP.URLDocumento;
            div_CURP.Visible = true;
            fu_CURP.Attributes.Remove("required");
            Documentos ClienteINE = new Documentos
            {
                IdTipoActor = 3,
                IdActor = Cliente.Id,
                IdTipoDocumento = 2
            };
            ClienteINE.ConsultarID();
            a_INE.HRef = ClienteINE.URLDocumento;
            div_INE.Visible = true;
            fu_INE.Attributes.Remove("required");
            Documentos ClienteComprobanteDomicilio = new Documentos
            {
                IdTipoActor = 3,
                IdActor = Cliente.Id,
                IdTipoDocumento = 5
            };
            ClienteComprobanteDomicilio.ConsultarID();
            a_ComprobanteDomicilio.HRef = ClienteComprobanteDomicilio.URLDocumento;
            div_ComprobanteDomicilio.Visible = true;
            fu_ComprobanteDomicilio.Attributes.Remove("required");
            Documentos ClienteComprobanteIngresos = new Documentos
            {
                IdTipoActor = 3,
                IdActor = Cliente.Id,
                IdTipoDocumento = 6
            };
            ClienteComprobanteIngresos.ConsultarID();
            a_ComprobanteIngresos.HRef = ClienteComprobanteIngresos.URLDocumento;
            div_ComprobanteIngresos.Visible = true;
            fu_ComprobanteIngresos.Attributes.Remove("required");
            //-------------------------------------------------------------------------------------------------
            b_Crear.Text = "Acutalizar Cliente";
        }

        /// <summary>
        /// MÉTODO PARA CREAR EL CLIENTE
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void b_Crear_Click(object sender, EventArgs e)
        {
            if (ValidarInformacion())
            {
                try
                {
                    if (Request.QueryString["id"] == null)
                    {
                        Crear();
                    }
                    else
                    {
                        Actualizar();
                    }
                }
                catch (Exception ex)
                {
                    Literal literal = (Literal)Master.FindControl("lAlerta");
                    literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA ACTUALIZAR AL CLIENTE
        /// </summary>
        private void Actualizar()
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            //PROMOTOR
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Clientes Cliente = new App_Code.Entidades.Clientes
            {
                Id = ID,
                IdUsuario = IDUsuarioActual
            };
            Cliente.ConsultarID();
            ClienteFormulario(Cliente);
            Cliente.Actualizar();
            //REDES SOCIALES DEL CLIENTE
            GuardarRedSocial(Cliente.Id, 3, 1, tb_Facebook.Text);
            GuardarRedSocial(Cliente.Id, 3, 2, tb_Twitter.Text);
            GuardarRedSocial(Cliente.Id, 3, 3, tb_Instagram.Text);
            //DOCUMENTOS DEL CLIENTE
            GuardarDocumentos(Cliente.Id, 3, 1, fu_ActaNacimiento.FileName);
            GuardarDocumentos(Cliente.Id, 3, 2, fu_INE.FileName);
            GuardarDocumentos(Cliente.Id, 3, 3, fu_CURP.FileName);
            GuardarDocumentos(Cliente.Id, 3, 4, fu_ConstanciaResidencia.FileName);
            GuardarDocumentos(Cliente.Id, 3, 5, fu_ComprobanteDomicilio.FileName);
            GuardarDocumentos(Cliente.Id, 3, 6, fu_ComprobanteIngresos.FileName);

            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Cliente actualizado correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        /// <summary>
        /// MÉTODO PARA CREAR AL CLIENTE
        /// </summary>
        private void Crear()
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            //PROMOTOR
            App_Code.Entidades.Clientes Cliente = new App_Code.Entidades.Clientes(IDUsuarioActual);
            ClienteFormulario(Cliente);
            Cliente.Agregar();
            //REDES SOCIALES DEL PROMOTOR
            GuardarRedSocial(Cliente.Id, 3, 1, tb_Facebook.Text);
            GuardarRedSocial(Cliente.Id, 3, 2, tb_Twitter.Text);
            GuardarRedSocial(Cliente.Id, 3, 3, tb_Instagram.Text);
            //DOCUMENTOS DEL PROMOTOR
            GuardarDocumentos(Cliente.Id, 3, 1, fu_ActaNacimiento.FileName);
            GuardarDocumentos(Cliente.Id, 3, 2, fu_INE.FileName);
            GuardarDocumentos(Cliente.Id, 3, 3, fu_CURP.FileName);
            GuardarDocumentos(Cliente.Id, 3, 4, fu_ConstanciaResidencia.FileName);
            GuardarDocumentos(Cliente.Id, 3, 5, fu_ComprobanteDomicilio.FileName);
            GuardarDocumentos(Cliente.Id, 3, 6, fu_ComprobanteIngresos.FileName);

            Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Cliente creado correctamente.", 3);
            Response.Redirect("Listado.aspx");
        }

        /// <summary>
        /// MÉTODO PARA GUARDAR LOS DOCUMENTOS
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idTipoActor"></param>
        /// <param name="idTipoDocumento"></param>
        /// <param name="URL"></param>
        private void GuardarDocumentos(int? id, int idTipoActor, int idTipoDocumento, string URL)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            Documentos Documento = new Documentos
            {
                IdTipoActor = idTipoActor,
                IdActor = id,
                IdTipoDocumento = idTipoDocumento,
                IdUsuario = IDUsuarioActual
            };
            Documento.ConsultarID();
            Documento.URLDocumento = URL;
            if (Documento.Id == null)
            {
                Documento.Agregar();
            }
            else
            {
                if (URL != string.Empty)
                {
                    Documento.Actualizar();
                }
            }
        }

        /// <summary>
        /// MÉTODO PARA GUARDAR LAS REDES SOCIALES
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idTipoActor"></param>
        /// <param name="idTipoRed"></param>
        /// <param name="URL"></param>
        private void GuardarRedSocial(int? id, int idTipoActor, int idTipoRed, string URL)
        {
            int IDUsuarioActual = 0;
            int.TryParse(((Usuarios)Session["Usuario"]).Id.ToString(), out IDUsuarioActual);
            RedesSociales Red = new RedesSociales
            {
                IdActor = id,
                IdTipoActor = idTipoActor,
                IdTipoRedSocial = idTipoRed,
                IdUsuario = IDUsuarioActual
            };
            Red.ConsultarID();
            Red.URL = URL;
            if (Red.Id == null)
            {
                Red.Agregar();
            }
            else
            {
                Red.Actualizar();
            }
        }

        /// <summary>
        /// MÉTODO PARA OBTENER EL CLIENTE COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private void ClienteFormulario(App_Code.Entidades.Clientes Cliente)
        {
            Cliente.Nombre = tb_Nombre.Text;
            Cliente.TelefonoCasa = tb_TelefonoCasa.Text;
            Cliente.TelefonoCelular = tb_TelefonoCelular.Text;
            Cliente.Direccion = tb_Direccion.Text;
            Cliente.FechaNacimiento = DateTime.Parse(tb_FechaNacimiento.Text);
            Cliente.CorreoElectronico = tb_CorreoElectronico.Text;
            if (fu_Foto.HasFile)
            {
                Cliente.URLFoto = fu_Foto.FileName;
            }
            Cliente.NombreEmpresa = tb_NombreEmpresa.Text;
            Cliente.PuestoEmpresa = tb_PuestoEmpresa.Text;
            Cliente.DireccionEmpresa = tb_DireccionEmpresa.Text;
            Cliente.HorarioEmpresa = tb_HorarioEmpresa.Text;
            Cliente.Antiguedad = tb_AntiguedadEmpresa.Text;
            Cliente.TelefonoEmpresa = tb_TelefonoEmpresa.Text;
            Cliente.SueldoMensual = tb_SueldoMensualEmpresa.Text;
            Cliente.NombreJefe = tb_NombreJefeEmpresa.Text;
            Cliente.TelefonoJefe = tb_TelefonoJefeEmpresa.Text;
        }

        /// <summary>
        /// FUNCIÓN PARA VALIDAR LA INFORMACIÓN DEL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private bool ValidarInformacion()
        {
            try
            {
                DateTime fechaNacimiento = DateTime.Parse(tb_FechaNacimiento.Text);
                if (Request.QueryString["id"] == null)
                {
                    if (!fu_Foto.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la foto del promotor.");
                    }
                    /*DOCUMENTOS DEL CLIENTE*/
                    if (!fu_ActaNacimiento.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el acta de nacimiento del promotor.");
                    }
                    if (!fu_ConstanciaResidencia.HasFile)
                    {
                        throw new Exception("Por favor, ingrese la constancia de residencia del promotor.");
                    }
                    if (!fu_CURP.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el CURP del promotor.");
                    }
                    if (!fu_INE.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el INE del promotor.");
                    }
                    if (!fu_ComprobanteDomicilio.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el comprobante de domicilio del promotor.");
                    }
                    if (!fu_ComprobanteIngresos.HasFile)
                    {
                        throw new Exception("Por favor, ingrese el comprobante de domicilio del promotor.");
                    }
                    /*/DOCUMENTOS DEL CLIENTE*/
                }
                return true;
            }
            catch (Exception ex)
            {
                Literal literal = (Literal)Master.FindControl("lAlerta");
                literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
                return false;
            }
        }
    }
}