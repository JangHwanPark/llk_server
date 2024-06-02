import Router from 'koa-router';
import homeController from '../controllers/homeController.js';
import userRoutes from './userRoutes.js';

const router = new Router();

router.get('/', homeController.index);

// 사용자 라우트 추가
router.use('/users', userRoutes.routes(), userRoutes.allowedMethods());

export default router;