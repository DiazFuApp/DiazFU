using System.Configuration;

namespace WebAPI.App_Start
{
    public class Conexion
    {
        public static string CadenaConexion()
        {
            return ConfigurationManager.ConnectionStrings["DIAZFU_DB"].ConnectionString;
        }
    }
}