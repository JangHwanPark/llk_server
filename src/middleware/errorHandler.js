const errorHandler = async (ctx, next) => {
    try {
        await next();
    } catch (error) {
        ctx.status = error.status || 500;
        ctx.body = error.message || "Internal Server Error";
        ctx.app.emit("error", error, ctx);
    }
};

export default errorHandler;