angular.module("coterie.services", ["ngResource"]).factory("Coterie", function ($resource) {
    return $resource("./coteries/:id", {
        id: '@id'
    }, {
        update: {
            method: "PUT"
        },
        remove: {
            method: "DELETE"
        }
    });
});