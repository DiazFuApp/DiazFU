using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Pagos
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdPrestamo;

        public int? IdPrestamo
        {
            get { return _IdPrestamo; }
            set { _IdPrestamo = value; }
        }

        private int? _IdCliente;

        public int? IdCliente
        {
            get { return _IdCliente; }
            set { _IdCliente = value; }
        }

        private int? _IdTipoPrestamo;

        public int? IdTipoPrestamo
        {
            get { return _IdTipoPrestamo; }
            set { _IdTipoPrestamo = value; }
        }

        private float _Monto;

        public float Monto
        {
            get { return _Monto; }
            set { _Monto = value; }
        }

        private string _Plazo;

        public string Plazo
        {
            get { return _Plazo; }
            set { _Plazo = value; }
        }

        private string _TipoPago;

        public string TipoPago
        {
            get { return _TipoPago; }
            set { _TipoPago = value; }
        }

        private DateTime? _FechaProgramada;

        public DateTime? FechaProgramada
        {
            get { return _FechaProgramada; }
            set { _FechaProgramada = value; }
        }
        private DateTime? _FechaPago;

        public DateTime? FechaPago
        {
            get { return _FechaPago; }
            set { _FechaPago = value; }
        }

        private float _Morosidad;

        public float Morosidad
        {
            get { return _Morosidad; }
            set { _Morosidad = value; }
        }

        private string _Descripcion;

        public string Descripcion
        {
            get { return _Descripcion; }
            set { _Descripcion = value; }
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
        public Pagos()
        {

        }

        public Pagos(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un pago.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un pago.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los pagos activos.
        /// </summary>
        /// <returns>Lista con todos los pagos activos.</returns>
        public List<Pagos> ConsultarTodo()
        {
            List<Pagos> Pagos = new List<Pagos>();
            Pagos Pago = new Pagos();
            using (DataSet Consulta = Pago.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Pagos obj = new Pagos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdPrestamo = int.Parse(Fila["IdPrestamo"].ToString()),
                        IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                        IdTipoPrestamo = int.Parse(Fila["IdTipoPrestamo"].ToString()),
                        Monto = float.Parse(Fila["Monto"].ToString()),
                        Plazo = Fila["Plazo"].ToString(),
                        TipoPago = Fila["TipoPago"].ToString(),
                        FechaProgramada = DateTime.Parse(Fila["FechaProgramada"].ToString()),
                        FechaPago = DateTime.Parse(Fila["FechaPago"].ToString()),
                        Morosidad = float.Parse(Fila["Morosidad"].ToString()),
                        Descripcion = Fila["Descripcion"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Pagos.Add(obj);
                }
            }
            return Pagos;
        }

        /// <summary>
        /// Método para consultar un pago según su ID.
        /// </summary>
        /// <returns>Lista con todos los pagos activos.</returns>
        public List<Pagos> Consultar()
        {
            List<Pagos> Pagos = new List<Pagos>();
            Pagos Pago = new Pagos();
            using (DataSet Consulta = Pago.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Pagos obj = new Pagos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdPrestamo = int.Parse(Fila["IdPrestamo"].ToString()),
                        IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                        IdTipoPrestamo = int.Parse(Fila["IdTipoPrestamo"].ToString()),
                        Monto = float.Parse(Fila["Monto"].ToString()),
                        Plazo = Fila["Plazo"].ToString(),
                        TipoPago = Fila["TipoPago"].ToString(),
                        FechaProgramada = DateTime.Parse(Fila["FechaProgramada"].ToString()),
                        FechaPago = DateTime.Parse(Fila["FechaPago"].ToString()),
                        Morosidad = float.Parse(Fila["Morosidad"].ToString()),
                        Descripcion = Fila["Descripcion"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Pagos.Add(obj);
                }
            }
            return Pagos;
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
                new SqlParameter("@IdPrestamo", IdPrestamo),
                new SqlParameter("@IdCliente", IdCliente),
                new SqlParameter("@IdTipoPrestamo", IdTipoPrestamo),
                new SqlParameter("@Monto", Monto),
                new SqlParameter("@Plazo", Plazo),
                new SqlParameter("@TipoPago", TipoPago),
                new SqlParameter("@FechaProgramada", FechaProgramada),
                new SqlParameter("@FechaPago", FechaPago),
                new SqlParameter("@Morosidad", Morosidad),
                new SqlParameter("@Descripcion", Descripcion),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPagos]", Parametros.ToArray());
        }
        #endregion
    }
}