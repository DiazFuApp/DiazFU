using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
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
        /// <returns>Data Set con todos los pagos activos.</returns>
        public DataSet ConsultarTodo()
        {
            this.Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un pago según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.IdPrestamo = int.Parse(Fila["IdPrestamo"].ToString());
                this.IdCliente = int.Parse(Fila["IdCliente"].ToString());
                this.IdTipoPrestamo = int.Parse(Fila["IdTipoPrestamo"].ToString());
                this.Monto = float.Parse(Fila["Monto"].ToString());
                this.Plazo = Fila["Plazo"].ToString();
                this.TipoPago = Fila["TipoPago"].ToString();
                this.FechaProgramada = DateTime.Parse(Fila["FechaProgramada"].ToString());
                this.FechaPago = DateTime.Parse(Fila["FechaPago"].ToString());
                this.Morosidad = float.Parse(Fila["Morosidad"].ToString());
                this.Descripcion = Fila["Descripcion"].ToString();
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
            Parametros.Add(new SqlParameter("@IdPrestamo", IdPrestamo));
            Parametros.Add(new SqlParameter("@IdCliente", IdCliente));
            Parametros.Add(new SqlParameter("@IdTipoPrestamo", IdTipoPrestamo));
            Parametros.Add(new SqlParameter("@Monto", Monto));
            Parametros.Add(new SqlParameter("@Plazo", Plazo));
            Parametros.Add(new SqlParameter("@TipoPago", TipoPago));
            Parametros.Add(new SqlParameter("@FechaProgramada", FechaProgramada));
            Parametros.Add(new SqlParameter("@FechaPago", FechaPago));
            Parametros.Add(new SqlParameter("@Morosidad", Morosidad));
            Parametros.Add(new SqlParameter("@Descripcion", Descripcion));
            Parametros.Add(new SqlParameter("@IdEstatus", IdEstatus));
            Parametros.Add(new SqlParameter("@IdUsuarioActual", IdUsuario));

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPagos]", Parametros.ToArray());
        }
        #endregion
    }
}