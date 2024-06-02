import Koa from "koa";

const app = new Koa();

app.use(ctx => {
    ctx.body = 'Test';
});

app.listen(4000, () => {
    console.log("Server test")
});