import Router from "koa-router";
import userController from "../controllers/userController.js";

const router = new Router();

router.get("/", userController.list);
router.get("/:id", userController.get);

export default router;