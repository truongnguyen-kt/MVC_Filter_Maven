/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blue.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author 12345
 */
public class DispatchFilter implements Filter{

    private static final boolean debug = true;
    // if fiter configuration object is null, this filter is
    // not currently configured
    private FilterConfig filterConfig = null;

    public DispatchFilter() {
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig != null){
            if(!debug){
                log("DispatchFilter:Initializing filter");
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url;
        try {
            //get site map
            ServletContext context = req.getServletContext();
            Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
            
            // get resource name
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            
            // get site mapping
            url = siteMap.getProperty(resource);
            if(url != null){
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else{
                chain.doFilter(request, response);
            }
            
        } catch (Throwable ex) {
            log(ex.getMessage());
        }
    }

    // return filter config
    public FilterConfig getFilterConfig(){
        return this.filterConfig;
    }
    
    // set filter config
    public void setFilterConfig(FilterConfig fc){
        this.filterConfig = fc;
    }
    
    @Override
    public void destroy() {
    }
    
    // return a string representation of this object
    @Override
    public String toString(){
        if(filterConfig != null){
            return ("DispatchFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatchFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return sb.toString();
    }
    
    public void log(String msg){
        filterConfig.getServletContext().log(msg);
    }
    
}
