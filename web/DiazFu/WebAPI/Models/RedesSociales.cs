using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
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
        /// <returns>Lista con todas las redes sociales activas.</returns>
        public List<RedesSociales> ConsultarTodo()
        {
            List<RedesSociales> Redes = new List<RedesSociales>();
            RedesSociales Red = new RedesSociales();
            using (DataSet Consulta = Red.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    RedesSociales obj = new RedesSociales
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        URL = Fila["URL"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Redes.Add(obj);
                }
            }
            return Redes;
        }

        /// <summary>
        /// Método para consultar una red social según su ID.
        /// </summary>
        /// <returns>Lista con todas las redes sociales del actor.</returns>
        public List<RedesSociales> Consultar()
        {
            List<RedesSociales> Redes = new List<RedesSociales>();
            RedesSociales Red = new RedesSociales();
            using (DataSet Consulta = Red.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    RedesSociales obj = new RedesSociales
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        URL = Fila["URL"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Redes.Add(obj);
                }
            }
            return Redes;
        }

        /// <summary>
        /// Método para consultar todas las redes sociales de las referencias del promotor
        /// </summary>
        /// <returns></returns>
        public List<RedesSociales> ConsultarRedesSocialesReferenciasPromotores()
        {
            List<RedesSociales> Redes = new List<RedesSociales>();
            RedesSociales Red = new RedesSociales();
            using (DataSet Consulta = Red.EjecutarSP(4))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    RedesSociales obj = new RedesSociales
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        URL = Fila["URL"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Redes.Add(obj);
                }
            }
            return Redes;
        }

        /// <summary>
        /// Función para consultar todas las redes sociales de las referencias del préstamo
        /// </summary>
        /// <returns></returns>
        public List<RedesSociales> ConsultarRedesSocialesReferenciasPrestamos()
        {
            List<RedesSociales> Redes = new List<RedesSociales>();
            RedesSociales Red = new RedesSociales();
            using (DataSet Consulta = Red.EjecutarSP(5))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    RedesSociales obj = new RedesSociales
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdTipoRedSocial = int.Parse(Fila["IdTipoRedSocial"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        URL = Fila["URL"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Redes.Add(obj);
                }
            }
            return Redes;
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