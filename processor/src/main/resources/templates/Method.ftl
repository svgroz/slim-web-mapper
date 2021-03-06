    @Override
    protected void ${prefix}(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final var reqPath = req.getRequestURI();
        switch (reqPath) {
            <#list methods as method>
            <#list method.urls as url>
            case "${ctx.rootUrl}${url}":
            </#list>
                RequestResponseUtils.processFunction(req, resp, this::${prefix}_${method.methodName}, serializer);
                return;
            </#list>
            default:
                RequestResponseUtils.handleNotFound(resp);
                return;
        }
    }