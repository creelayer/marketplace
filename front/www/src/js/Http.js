const METHOD_GET = 'GET';
const METHOD_POST = 'POST';
const METHOD_DELETE = 'DELETE';

export default {
    get,
    post,
    del,
    request
    // put,
};

function get(url) {
    return this.request(METHOD_GET, url)
}

function post(url, body) {
    return this.request(METHOD_POST, url, body)
}

function del(url) {
    return this.request(METHOD_DELETE, url)
}

function request(method, url, body) {
    const requestOptions = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body)
    };

    function onResponse(response) {

        if (response.ok) {
            return response;
        }

        if (response.status === 401) {
            // console.log(that)
        }

        throw Error(response.statusText);
    }

    return fetch(url, requestOptions).then(onResponse).then(res => res.json());
}
