    @Override
    protected void ${methodName}(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String reqPath = req.getRequestURI();
        <#list methods as method>
        if (_${method.methodName}GetMappings.contains(reqPath)) {
        <#list method.argumentList as arg>
            var _${arg.argumentName} = RequestParameterMapper.${argTypeToMethod[arg.argumentType]}("${arg.argumentName}", req);
        </#list>
            target.${method.methodName}(<#list method.argumentList as arg>_${arg.argumentName}<#sep>, </#sep></#list>);
        }
        </#list>
    }
