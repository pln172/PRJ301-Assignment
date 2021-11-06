package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>SALE MANAGEMENT</title>\n");
      out.write("        <link rel=\"icon\" href=\"img/favicon.png\" type=\"image/x-icon\" sizes=\"16x16\">\n");
      out.write("        <Link rel = \"stylesheet\" href = \"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\"/>\n");
      out.write("        <link href=\"css/login_style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function show() {\n");
      out.write("                var type = document.getElementById(\"pass\").type;\n");
      out.write("\n");
      out.write("                if (type === \"text\") {\n");
      out.write("                    document.getElementById(\"pass\").type = \"password\";\n");
      out.write("                } else {\n");
      out.write("                    document.getElementById(\"pass\").type = \"text\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <form action=\"login\" method=\"POST\">\n");
      out.write("            <table>\n");
      out.write("                <caption id=\"caption\">SALE MANAGEMENT</caption>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>Username <span>*</span></td>\n");
      out.write("                    <td><input type=\"text\" name=\"user\" pattern=\"[a-zA-Z0-9]+\"\n");
      out.write("                               value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.user}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required/></td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>Password <span>*</span></td>\n");
      out.write("                    <td>\n");
      out.write("                        <input id=\"pass\" type=\"password\" name=\"pass\" \n");
      out.write("                               value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.pass}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" \n");
      out.write("                               pattern=\"[a-zA-Z0-9]+\" required/>\n");
      out.write("                        <span onclick=\"show()\" style=\"cursor: pointer;\">\n");
      out.write("                            <i class=\"fas fa-eye\"></i></span>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("            <div>\n");
      out.write("                <input type=\"checkbox\" name=\"remember\" value=\"yes\"\n");
      out.write("                       ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.remember == \"yes\" ? \"checked=\\\"checked\\\"\" : \"\"}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/>Remember me<br/>\n");
      out.write("                <input id=\"login\" type=\"submit\" value=\"LOGIN\"/><br/>\n");
      out.write("                <p\"><a href=\"forgetPass\">Forget password?</a></p>\n");
      out.write("                <p style=\"color: red; font-weight: bold;\">\n");
      out.write("                    ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.err}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                </p>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
