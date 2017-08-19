﻿using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class Usuarios
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
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

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
        }

        private string _Contrasena;

        public string Contrasena
        {
            get { return _Contrasena; }
            set { _Contrasena = value; }
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
        public Usuarios() { }

        public Usuarios(int IdUsuario)
        {
            this.IdUsuario = IdUsuario;
            this.IdEstatus = 1;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un usuario.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un usuario.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los usuarios activos.
        /// </summary>
        /// <returns>Data Set con todos los usuarios activos.</returns>
        public DataSet ConsultarTodo()
        {
            this.Id = null;
            return EjecutarSP(3);
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
                System.Data.DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.IdActor = int.Parse(Fila["IdActor"].ToString());
                this.IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                this.Nombre = Fila["Nombre"].ToString();
                this.Contrasena = Fila["Contrasena"].ToString();
                this.IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                this.Id = null;
            }
        }

        /// <summary>
        /// Método para consultar un usuario según su ID.
        /// </summary>
        public void LogIn()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(4);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.IdActor = int.Parse(Fila["IdActor"].ToString());
                this.IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                this.Nombre = Fila["Nombre"].ToString();
                this.Contrasena = Fila["Contrasena"].ToString();
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
            Parametros.Add(new SqlParameter("@IdActor", IdActor));
            Parametros.Add(new SqlParameter("@IdTipoActor", IdTipoActor));
            Parametros.Add(new SqlParameter("@Nombre", Nombre));
            Parametros.Add(new SqlParameter("@Contrasena", Contrasena));
            Parametros.Add(new SqlParameter("@IdEstatus", IdEstatus));
            Parametros.Add(new SqlParameter("@IdUsuarioActual", IdUsuario));

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[seguridad].[SPUsuarios]", Parametros.ToArray());
        }
        #endregion
    }
}