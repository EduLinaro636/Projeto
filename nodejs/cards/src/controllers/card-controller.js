const neDB = require('../configurations/database')
const api = {}

api.findAll = (request, response) => {
    let data = neDB.find({},request.body,function(exception,docs){
        let message = docs;
        let status = 200;

        if (exception){
            message = {"Houve um erro ao coletar dados: ":exception};
            status = 400
        }

        response.status(status);
        response.json(message);


    })

}

api.insert = (request, response) => {
    let insert = neDB.insert(request.body,function(exception,docs){
        let message = docs;
        let status = 201;

        if (exception){
            message = {"Houve um erro ao inserir dados: ":exception};
            status = 400
        }

        response.status(status);
        response.json(message);


    })

}

api.update = (request, response) => {
    let id = request.params.id;

    let update = neDB.update({_id:id},{ $set: request.body},{multi:true},function(exception,numreplaced){
        let message = {"Sucesso": "Um registro foi alterado com sucesso"};
        let status = 200;

        if (exception){
            message = {"Houve um erro ao atualizar dados: ":exception};
            status = 400
        }

        response.status(status);
        response.json(message);


    })

}

api.delete = (request, response) => {
    let id = request.params.id;

    let remove = neDB.remove({_id:id},{},function(exception,numdeleted){
        let message = {"Sucesso": "Um registro foi deletado com sucesso"};
        let status = 200;

        if (exception){
            message = {"Houve um erro ao deletar dados: ":exception};
            status = 400
        }

        response.status(status);
        response.json(message);


    })
    
}

api.get = (request, response) => {
    let id = request.params.id;

    let data = neDB.find({_id:id},{},function(exception,docs){
        let message = docs;
        let status = 200;

        if (exception){
            message = {"Houve um erro ao coletar dados individuais: ":exception};
            status = 400
        }

        response.status(status);
        response.json(message);


    })
    
    
}

api.pagination = (request, response) => {
        let data = neDB.find({}).sort({customerName:1}).skip(1).limit(1).exec( (exception, docs) => {
            let message = docs;
            let status = 200;

            if (exception){
                message = {"Houve um erro ao encontrar a página desejada: ":exception};
                status = 400
            }

            response.status(status);
            response.json(message);


      })
    
}



module.exports = api