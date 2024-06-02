const list = async (ctx) => {
    ctx.body = "List of users";
};

const get = async (ctx) => {
    const userId = ctx.params.id;
    ctx.body = `Details of user ${userId}`;
};

export default {
    list,
    get
}