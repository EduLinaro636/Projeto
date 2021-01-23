const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards')
        .get(api.findAll)
        .post(api.insert);
        
    app.route("/cards/:id").put(api.update).delete(api.delete).get(api.get);
    app.route("/cards/paginationAndSorting").get(api.pagination);
        
}