using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Promotores
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
        }

        private string _Direccion;

        public string Direccion
        {
            get { return _Direccion; }
            set { _Direccion = value; }
        }

        private string _TelefonoCasa;

        public string TelefonoCasa
        {
            get { return _TelefonoCasa; }
            set { _TelefonoCasa = value; }
        }

        private string _TelefonoCelular;

        public string TelefonoCelular
        {
            get { return _TelefonoCelular; }
            set { _TelefonoCelular = value; }
        }

        private string _CorreoElectronico;

        public string CorreoElectronico
        {
            get { return _CorreoElectronico; }
            set { _CorreoElectronico = value; }
        }

        private DateTime? _FechaNacimiento;

        public DateTime? FechaNacimiento
        {
            get { return _FechaNacimiento; }
            set { _FechaNacimiento = value; }
        }

        private string _RFC;

        public string RFC
        {
            get { return _RFC; }
            set { _RFC = value; }
        }

        private string _CURP;

        public string CURP
        {
            get { return _CURP; }
            set { _CURP = value; }
        }

        private string _ClaveElector;

        public string ClaveElector
        {
            get { return _ClaveElector; }
            set { _ClaveElector = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
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
        public Promotores()
        {

        }

        public Promotores(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un promotor.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un promotor.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los promotores activos.
        /// </summary>
        /// <returns>Lista con todos los promotores activos.</returns>
        public List<Promotores> ConsultarTodo()
        {
            List<Promotores> Promotores = new List<Promotores>();
            Promotores Promotor = new Promotores();
            using (DataSet Consulta = Promotor.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Promotores obj = new Promotores
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString()),
                        RFC = Fila["RFC"].ToString(),
                        CURP = Fila["CURP"].ToString(),
                        ClaveElector = Fila["ClaveElector"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Promotores.Add(obj);
                }
            }
            return Promotores;
        }

        /// <summary>
        /// Método para consultar un promotor.
        /// </summary>
        /// <returns>Lista con todos los promotores activos.</returns>
        public List<Promotores> Consultar()
        {
            List<Promotores> Promotores = new List<Promotores>();
            using (DataSet Consulta = EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    this.Id = int.Parse(Fila["Id"].ToString());
                    this.Nombre = Fila["Nombre"].ToString();
                    this.Direccion = Fila["Direccion"].ToString();
                    this.TelefonoCasa = Fila["TelefonoCasa"].ToString();
                    this.TelefonoCelular = Fila["TelefonoCelular"].ToString();
                    this.CorreoElectronico = Fila["CorreoElectronico"].ToString();
                    this.FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString());
                    this.RFC = Fila["RFC"].ToString();
                    this.CURP = Fila["CURP"].ToString();
                    this.ClaveElector = Fila["ClaveElector"].ToString();
                    this.URLFoto = Fila["URLFoto"].ToString();
                    this.IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
                    Promotores.Add(this);
                }
            }
            return Promotores;
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
                new SqlParameter("@Nombre", Nombre),
                new SqlParameter("@Direccion", Direccion),
                new SqlParameter("@TelefonoCasa", TelefonoCasa),
                new SqlParameter("@TelefonoCelular", TelefonoCelular),
                new SqlParameter("@CorreoElectronico", CorreoElectronico),
                new SqlParameter("@FechaNacimiento", FechaNacimiento),
                new SqlParameter("@RFC", RFC),
                new SqlParameter("@CURP", CURP),
                new SqlParameter("@ClaveElector", ClaveElector),
                new SqlParameter("@URLFoto", URLFoto),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPromotores]", Parametros.ToArray());
        }
        #endregion
    }
}