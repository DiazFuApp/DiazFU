using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class RedesSociales
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdTipoRedSocial;

        public int? IdTipoRedSocial
        {
            get { return _IdTipoRedSocial; }
            set { _IdTipoRedSocial = value; }
        }

        private int? _IdActor;

        public int? IdActor
        {
            get { return _IdActor; }
            set { _IdActor = value; }
        }

        private int? _IdTipoActor;

        public int? IdTipoActor
        {
            get { return _IdTipoActor; }
            set { _IdTipoActor = value; }
        }

        private string _URL;

        public string URL
        {
            get { return _URL; }
            set { _URL = value; }
        }

        private int _IdEstatus;

        public int IdEstatus
        {
            get { return _IdEstatus; }
            set { _IdEstatus = value; }
        }

        private int _IdUsuario;

        public int IdUsuario
        {
            get { return _IdUsuario; }
            set { _IdUsuario = value; }
        }
        #endregion

        #region Constructor
        public RedesSociales()
        {

        }

        public RedesSociales(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar una red social.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar una red social.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todas las redes sociales activas.
        /// </summary>
        /// <returns>Data Set con todas las actividades activas.</returns>
        public DataSet ConsultarTodo()
        {
            this.Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar una red social según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString());
                this.IdActor = int.Parse(Fila["IdActor"].ToString());
                this.IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                this.URL = Fila["URL"].ToString();
                this.IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                this.Id = null;
            }
        }

        /// <summary>
        /// Función para ejecutar el procedimiento almacenado seleccionado.
        /// </summary>
        /// <returns>Data Set con la consulta emitida por SQL</returns>
        public DataSet EjecutarSP(int Opcion)
        {
            List<SqlParameter> Parametros = new List<SqlParameter>();
            Parametros.Add(new SqlParameter("@Opcion", Opcion));
            Parametros.Add(new SqlParameter("@Id", Id));
            Parametros.Add(new SqlParameter("@IdTipoRedSocial", IdTipoRedSocial));
            Parametros.Add(new SqlParameter("@IdActor", IdActor));
            Parametros.Add(new SqlParameter("@IdTipoActor", IdTipoActor));
            Parametros.Add(new SqlParameter("@URL", URL));
            Parametros.Add(new SqlParameter("@IdEstatus", IdEstatus));
            Parametros.Add(new SqlParameter("@IdUsuarioActual", IdUsuario));

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPRedesSociales]", Parametros.ToArray());
        }
        #endregion
    }
}