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
            IdEstatus = 1;
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
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
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
            Id = null;
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
                Id = int.Parse(Fila["Id"].ToString());
                IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString());
                IdActor = int.Parse(Fila["IdActor"].ToString());
                IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                URL = Fila["URL"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Método para consultar todas las redes sociales de las referencias del promotor
        /// </summary>
        /// <returns></returns>
        public void ConsultarRedesSocialesReferenciasPromotores()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(4);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString());
                IdActor = int.Parse(Fila["IdActor"].ToString());
                IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                URL = Fila["URL"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Función para consultar todas las redes sociales de las referencias del préstamo
        /// </summary>
        /// <returns></returns>
        public void ConsultarRedesSocialesReferenciasPrestamos()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(5);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString());
                IdActor = int.Parse(Fila["IdActor"].ToString());
                IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                URL = Fila["URL"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Función para ejecutar el procedimiento almacenado seleccionado.
        /// </summary>
        /// <returns>Data Set con la consulta emitida por SQL</returns>
        public DataSet EjecutarSP(int Opcion)
        {
            List<SqlParameter> Parametros = new List<SqlParameter>
            {
                new SqlParameter("@Opcion", Opcion),
                new SqlParameter("@Id", Id),
                new SqlParameter("@IdTipoRedSocial", IdTipoRedSocial),
                new SqlParameter("@IdActor", IdActor),
                new SqlParameter("@IdTipoActor", IdTipoActor),
                new SqlParameter("@URL", URL),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPRedesSociales]", Parametros.ToArray());
        }
        #endregion
    }
}