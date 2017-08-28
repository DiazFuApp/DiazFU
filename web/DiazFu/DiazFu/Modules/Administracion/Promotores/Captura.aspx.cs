using DiazFu.App_Code.Utilerias;
using System;
using System.Web.UI.WebControls;
using DiazFu.App_Code.Entidades;

namespace DiazFu.Modules.Administracion.Promotores
{
    public partial class Captura : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["id"].ToString() != string.Empty)
                {
                    ConsultarPromotor();
                }
            }
        }

        /// <summary>
        /// MÉTODO ÁRA CONSULTAR UN PROMOTOR A EDITAR
        /// </summary>
        private void ConsultarPromotor()
        {
            int ID = int.Parse(Request.QueryString["id"].ToString());
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores();
            Promotor.Id = ID;
            Promotor.ConsultarID();

            //LLENAR FORMULARIO
            tb_Nombre.Text = Promotor.Nombre;
            tb_RFC.Text = Promotor.RFC;
            tb_Direccion.Text = Promotor.Direccion;
            tb_TelefonoCasa.Text = Promotor.TelefonoCasa;
            tb_TelefonoCelular.Text = Promotor.TelefonoCelular;
            tb_CorreoElectronico.Text = Promotor.CorreoElectronico;
            tb_FechaNacimiento.Text = Promotor.FechaNacimiento.ToString();
            tb_CURP.Text = Promotor.CURP;
            tb_ClaveElector.Text = Promotor.ClaveElector;
            //REDES

        }

        protected void b_Crear_Click(object sender, EventArgs e)
        {
            if (ValidarInformacion())
            {
                try
                {
                    //PROMOTOR
                    App_Code.Entidades.Promotores Promotor = PromotorFormulario();
                    Promotor.Agregar();
                    //REDES SOCIALES DEL PROMOTOR
                    GuardarRedSocial(Promotor.Id, 2, 1, tb_Facebook.Text);
                    GuardarRedSocial(Promotor.Id, 2, 2, tb_Twitter.Text);
                    GuardarRedSocial(Promotor.Id, 2, 3, tb_Instagram.Text);
                    //DOCUMENTOS DEL PROMOTOR
                    GuardarDocumentos(Promotor.Id, 2, 1, fu_ActaNacimiento.FileName);
                    GuardarDocumentos(Promotor.Id, 2, 2, fu_INE.FileName);
                    GuardarDocumentos(Promotor.Id, 2, 3, fu_CURP.FileName);
                    GuardarDocumentos(Promotor.Id, 2, 4, fu_ConstanciaResidencia.FileName);
                    GuardarDocumentos(Promotor.Id, 2, 5, fu_ComprobanteDomicilio.FileName);

                    //PRIMER REFERENCIA
                    ReferenciasPromotores Primera = PrimerReferenciaFormulario();
                    Primera.IdActor = Promotor.Id;
                    Primera.Agregar();
                    //REDES SOCIALES DE LA PRIMER REFERENCIA
                    GuardarRedSocial(Primera.Id, 5, 1, tb_pr_Facebook.Text);
                    GuardarRedSocial(Primera.Id, 5, 2, tb_pr_Twitter.Text);
                    GuardarRedSocial(Primera.Id, 5, 3, tb_pr_Instagram.Text);
                    //DOCUMENTOS DE LA PRIMER REFERENCIA
                    GuardarDocumentos(Primera.Id, 5, 1, fu_pr_ActaNacimiento.FileName);
                    GuardarDocumentos(Primera.Id, 5, 2, fu_pr_INE.FileName);
                    GuardarDocumentos(Primera.Id, 5, 3, fu_pr_CURP.FileName);
                    GuardarDocumentos(Primera.Id, 5, 4, fu_pr_ConstanciaResidencia.FileName);
                    GuardarDocumentos(Primera.Id, 5, 5, fu_pr_ComprobanteDomicilio.FileName);

                    //SEGUNDA REFERENCIA
                    ReferenciasPromotores Segunda = SegundaReferenciaFormulario();
                    Segunda.IdActor = Promotor.Id;
                    Segunda.Agregar();
                    //REDES SOCIALES DE LA SEGUNDA REFERENCIA
                    GuardarRedSocial(Segunda.Id, 5, 1, tb_sr_Facebook.Text);
                    GuardarRedSocial(Segunda.Id, 5, 2, tb_sr_Twitter.Text);
                    GuardarRedSocial(Segunda.Id, 5, 3, tb_sr_Instagram.Text);
                    //DOCUMENTOS DE LA SEGUNDA REFERENCIA
                    GuardarDocumentos(Segunda.Id, 5, 1, fu_sr_ActaNacimiento.FileName);
                    GuardarDocumentos(Segunda.Id, 5, 2, fu_sr_INE.FileName);
                    GuardarDocumentos(Segunda.Id, 5, 3, fu_sr_CURP.FileName);
                    GuardarDocumentos(Segunda.Id, 5, 4, fu_sr_ConstanciaResidencia.FileName);
                    GuardarDocumentos(Segunda.Id, 5, 5, fu_sr_ComprobanteDomicilio.FileName);

                    Session["Alerta"] = Herramientas.Alerta("Operación existosa!", "Promotor creado correctamente.", 3);
                    Response.Redirect("Listado.aspx");
                }
                catch (Exception ex)
                {
                    Literal literal = (Literal)Master.FindControl("lAlerta");
                    literal.Text = Herramientas.Alerta("Oops, ocurrió un error!", ex.Message, 4);
                }
            }
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
            Documentos Documento = new Documentos();
            Documento.IdTipoActor = idTipoActor;
            Documento.IdActor = id;
            Documento.IdTipoDocumento = idTipoDocumento;
            Documento.URLDocumento = URL;
            Documento.Agregar();
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
            RedesSociales Red = new RedesSociales();
            Red.IdActor = id;
            Red.IdTipoActor = idTipoActor;
            Red.IdTipoRedSocial = idTipoRed;
            Red.URL = URL;
            Red.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER EL PROMOTOR COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private App_Code.Entidades.Promotores PromotorFormulario()
        {
            App_Code.Entidades.Promotores Promotor = new App_Code.Entidades.Promotores();
            Promotor.Nombre = tb_Nombre.Text;
            Promotor.RFC = tb_RFC.Text;
            Promotor.Direccion = tb_Direccion.Text;
            Promotor.TelefonoCasa = tb_TelefonoCasa.Text;
            Promotor.TelefonoCelular = tb_TelefonoCelular.Text;
            Promotor.CorreoElectronico = tb_CorreoElectronico.Text;
            Promotor.FechaNacimiento = DateTime.Parse(tb_FechaNacimiento.Text);
            Promotor.CURP = tb_CURP.Text;
            Promotor.ClaveElector = tb_ClaveElector.Text;
            return Promotor;
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER LA PRIMER REFERENCIA COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private ReferenciasPromotores PrimerReferenciaFormulario()
        {
            ReferenciasPromotores Referencia = new ReferenciasPromotores();
            Referencia.Nombre = tb_pr_Nombre.Text;
            Referencia.RFC = tb_pr_RFC.Text;
            Referencia.Direccion = tb_pr_Direccion.Text;
            Referencia.TelefonoCasa = tb_pr_TelefonoCasa.Text;
            Referencia.TelefonoCelular = tb_pr_TelefonoCelular.Text;
            Referencia.CorreoElectronico = tb_pr_CorreoElectronico.Text;
            Referencia.FechaNacimiento = DateTime.Parse(tb_pr_FechaNacimiento.Text);
            Referencia.CURP = tb_pr_CURP.Text;
            Referencia.ClaveElector = tb_pr_ClaveElector.Text;
            return Referencia;
        }

        /// <summary>
        /// FUNCIÓN PARA OBTENER LA SEGUNDA REFERENCIA COMO RESULTADO DE LO INGRESADO EN EL FORMULARIO
        /// </summary>
        /// <returns></returns>
        private ReferenciasPromotores SegundaReferenciaFormulario()
        {
            ReferenciasPromotores Referencia = new ReferenciasPromotores();
            Referencia.Nombre = tb_sr_Nombre.Text;
            Referencia.RFC = tb_sr_RFC.Text;
            Referencia.Direccion = tb_sr_Direccion.Text;
            Referencia.TelefonoCasa = tb_sr_TelefonoCasa.Text;
            Referencia.TelefonoCelular = tb_sr_TelefonoCelular.Text;
            Referencia.CorreoElectronico = tb_sr_CorreoElectronico.Text;
            Referencia.FechaNacimiento = DateTime.Parse(tb_sr_FechaNacimiento.Text);
            Referencia.CURP = tb_sr_CURP.Text;
            Referencia.ClaveElector = tb_sr_ClaveElector.Text;
            return Referencia;
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
                DateTime pr_fechaNacimiento = DateTime.Parse(tb_pr_FechaNacimiento.Text);
                DateTime sr_fechaNacimiento = DateTime.Parse(tb_sr_FechaNacimiento.Text);
                /*DOCUMENTOS DEL PROMOTOR*/
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
                /*/DOCUMENTOS DEL PROMOTOR*/
                /*DOCUMENTOS DE LA PRIMER REFERENCIA*/
                if (!fu_pr_ActaNacimiento.HasFile)
                {
                    throw new Exception("Por favor, ingrese el acta de nacimiento de la primer referencia.");
                }
                if (!fu_pr_ConstanciaResidencia.HasFile)
                {
                    throw new Exception("Por favor, ingrese la constancia de residencia de la primer referencia.");
                }
                if (!fu_pr_CURP.HasFile)
                {
                    throw new Exception("Por favor, ingrese el CURP de la primer referencia.");
                }
                if (!fu_pr_INE.HasFile)
                {
                    throw new Exception("Por favor, ingrese el INE de la primer referencia.");
                }
                if (!fu_pr_ComprobanteDomicilio.HasFile)
                {
                    throw new Exception("Por favor, ingrese el comprobante de domicilio de la primer referencia.");
                }
                /*/DOCUMENTOS DE LA PRIMER REFERENCIA*/
                /*DOCUMENTOS DE LA SEGUNDA REFERENCIA*/
                if (!fu_sr_ActaNacimiento.HasFile)
                {
                    throw new Exception("Por favor, ingrese el acta de nacimiento de la segunda referencia.");
                }
                if (!fu_sr_ConstanciaResidencia.HasFile)
                {
                    throw new Exception("Por favor, ingrese la constancia de residencia de la segunda referencia.");
                }
                if (!fu_sr_CURP.HasFile)
                {
                    throw new Exception("Por favor, ingrese el CURP de la segunda referencia.");
                }
                if (!fu_sr_INE.HasFile)
                {
                    throw new Exception("Por favor, ingrese el INE de la segunda referencia.");
                }
                if (!fu_sr_ComprobanteDomicilio.HasFile)
                {
                    throw new Exception("Por favor, ingrese el comsrobante de domicilio de la segunda referencia.");
                }
                /*/DOCUMENTOS DE LA SEGUNDA REFERENCIA*/
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