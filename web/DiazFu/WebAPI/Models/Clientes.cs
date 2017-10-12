using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Clientes
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

        private string _Direccion;

        public string Direccion
        {
            get { return _Direccion; }
            set { _Direccion = value; }
        }

        private DateTime? _FechaNacimiento;

        public DateTime? FechaNacimiento
        {
            get { return _FechaNacimiento; }
            set { _FechaNacimiento = value; }
        }

        private string _CorreoElectronico;

        public string CorreoElectronico
        {
            get { return _CorreoElectronico; }
            set { _CorreoElectronico = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
        }

        private string _NombreEmpresa;

        public string NombreEmpresa
        {
            get { return _NombreEmpresa; }
            set { _NombreEmpresa = value; }
        }

        private string _PuestoEmpresa;

        public string PuestoEmpresa
        {
            get { return _PuestoEmpresa; }
            set { _PuestoEmpresa = value; }
        }

        private string _DireccionEmpresa;

        public string DireccionEmpresa
        {
            get { return _DireccionEmpresa; }
            set { _DireccionEmpresa = value; }
        }

        private string _HorarioEmpresa;

        public string HorarioEmpresa
        {
            get { return _HorarioEmpresa; }
            set { _HorarioEmpresa = value; }
        }

        private string _Antiguedad;

        public string Antiguedad
        {
            get { return _Antiguedad; }
            set { _Antiguedad = value; }
        }

        private string _TelefonoEmpresa;

        public string TelefonoEmpresa
        {
            get { return _TelefonoEmpresa; }
            set { _TelefonoEmpresa = value; }
        }

        private string _SueldoMensual;

        public string SueldoMensual
        {
            get { return _SueldoMensual; }
            set { _SueldoMensual = value; }
        }

        private string _NombreJefe;

        public string NombreJefe
        {
            get { return _NombreJefe; }
            set { _NombreJefe = value; }
        }

        private string _TelefonoJefe;

        public string TelefonoJefe
        {
            get { return _TelefonoJefe; }
            set { _TelefonoJefe = value; }
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
        public Clientes()
        {

        }

        public Clientes(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un cliente.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un cliente.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los clientes activos.
        /// </summary>
        /// <returns>Lista con todos los clientes activos.</returns>
        public List<Clientes> ConsultarTodo()
        {
            List<Clientes> Clientes = new List<Clientes>();
            Clientes Cliente = new Clientes();
            using (DataSet Consulta = Cliente.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Clientes obj = new Clientes
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString()),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        NombreEmpresa = Fila["NombreEmpresa"].ToString(),
                        PuestoEmpresa = Fila["PuestoEmpresa"].ToString(),
                        DireccionEmpresa = Fila["DireccionEmpresa"].ToString(),
                        HorarioEmpresa = Fila["HorarioEmpresa"].ToString(),
                        Antiguedad = Fila["Antiguedad"].ToString(),
                        TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString(),
                        SueldoMensual = Fila["SueldoMensual"].ToString(),
                        NombreJefe = Fila["NombreJefe"].ToString(),
                        TelefonoJefe = Fila["TelefonoJefe"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Clientes.Add(obj);
                }
            }
            return Clientes;
        }

        /// <summary>
        /// Método para consultar un usuario según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Nombre = Fila["Nombre"].ToString();
                TelefonoCasa = Fila["TelefonoCasa"].ToString();
                TelefonoCelular = Fila["TelefonoCelular"].ToString();
                Direccion = Fila["Direccion"].ToString();
                FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString());
                CorreoElectronico = Fila["CorreoElectronico"].ToString();
                URLFoto = Fila["URLFoto"].ToString();
                NombreEmpresa = Fila["NombreEmpresa"].ToString();
                PuestoEmpresa = Fila["PuestoEmpresa"].ToString();
                DireccionEmpresa = Fila["DireccionEmpresa"].ToString();
                HorarioEmpresa = Fila["HorarioEmpresa"].ToString();
                Antiguedad = Fila["Antiguedad"].ToString();
                TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString();
                SueldoMensual = Fila["SueldoMensual"].ToString();
                NombreJefe = Fila["NombreJefe"].ToString();
                TelefonoJefe = Fila["TelefonoJefe"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Función para consultar una referencia
        /// </summary>
        /// <returns>Lista con los clientes que coinciden con el objeto enviado</returns>
        public List<Clientes> Consultar()
        {
            List<Clientes> Clientes = new List<Clientes>();
            Clientes Cliente = new Clientes();
            using (DataSet Consulta = Cliente.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Clientes obj = new Clientes
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        TelefonoCasa = Fila["TelefonoCasa"].ToString(),
                        TelefonoCelular = Fila["TelefonoCelular"].ToString(),
                        Direccion = Fila["Direccion"].ToString(),
                        FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString()),
                        CorreoElectronico = Fila["CorreoElectronico"].ToString(),
                        URLFoto = Fila["URLFoto"].ToString(),
                        NombreEmpresa = Fila["NombreEmpresa"].ToString(),
                        PuestoEmpresa = Fila["PuestoEmpresa"].ToString(),
                        DireccionEmpresa = Fila["DireccionEmpresa"].ToString(),
                        HorarioEmpresa = Fila["HorarioEmpresa"].ToString(),
                        Antiguedad = Fila["Antiguedad"].ToString(),
                        TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString(),
                        SueldoMensual = Fila["SueldoMensual"].ToString(),
                        NombreJefe = Fila["NombreJefe"].ToString(),
                        TelefonoJefe = Fila["TelefonoJefe"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Clientes.Add(obj);
                }
            }
            return Clientes;
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
                new SqlParameter("@TelefonoCasa", TelefonoCasa),
                new SqlParameter("@TelefonoCelular", TelefonoCelular),
                new SqlParameter("@Direccion", Direccion),
                new SqlParameter("@FechaNacimiento", FechaNacimiento),
                new SqlParameter("@CorreoElectronico", CorreoElectronico),
                new SqlParameter("@URLFoto", URLFoto),
                new SqlParameter("@NombreEmpresa", NombreEmpresa),
                new SqlParameter("@PuestoEmpresa", PuestoEmpresa),
                new SqlParameter("@DireccionEmpresa", DireccionEmpresa),
                new SqlParameter("@HorarioEmpresa", HorarioEmpresa),
                new SqlParameter("@Antiguedad", Antiguedad),
                new SqlParameter("@TelefonoEmpresa", TelefonoEmpresa),
                new SqlParameter("@SueldoMensual", SueldoMensual),
                new SqlParameter("@NombreJefe", NombreJefe),
                new SqlParameter("@TelefonoJefe", TelefonoJefe),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPClientes]", Parametros.ToArray());
        }
        #endregion
    }
}