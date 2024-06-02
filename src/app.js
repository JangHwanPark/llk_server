import Koa from "koa";
import bodyParser from 'koa-bodyparser';
import logger from './middleware/logger.js';
import errorHandler from './middleware/errorHandler.js';
import router from './routes/index.js';

const app = new Koa();

// 미들웨어 설정
app.use(bodyParser());
app.use(logger);
app.use(errorHandler);

// 라우터 설정
app.use(router.routes()).use(router.allowedMethods());

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});