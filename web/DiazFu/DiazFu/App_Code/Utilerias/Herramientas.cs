namespace DiazFu.App_Code.Utilerias
{
    public static class Herramientas
    {
        public static string Alerta(string titulo, string mensaje, int tipo)
        {
            string tipoAlerta;
            switch (tipo)
            {
                case 1:
                    tipoAlerta = "primary";
                    break;
                case 2:
                    tipoAlerta = "secondary";
                    break;
                case 3:
                    tipoAlerta = "success";
                    break;
                case 4:
                    tipoAlerta = "danger";
                    break;
                case 5:
                    tipoAlerta = "warning";
                    break;
                default:
                    tipoAlerta = "primary";
                    break;
            }
            string js = "<div class='row justify-content-end fixed-top m-3 div-alertas'>";
            js += "<div class='col-4 align-self-end'>";
            js += "<div class='alert alert-" + tipoAlerta + " fade show text-left' role = 'alert' >";
            js += "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>";
            js += "<span aria-hidden='true'>&times;</span>";
            js += "</button>";
            js += "<span class='font-weight-bold'>" + titulo + "</span> <br/>";
            js += "<span>" + mensaje + "</span>";
            js += "</div>";
            js += "</div>";
            js += "</div>";
            return js;
        }
    }
}