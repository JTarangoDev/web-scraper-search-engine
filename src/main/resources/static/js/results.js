let URL_API = "http://localhost:8080/api/"

let searchInput = document.getElementById("search-input");

let query = window.location.href.split("?query=")[1];

const loadResults = (resultList) => {

    let html = ''

    resultList.map((result) => {
        html += `<div class="result">
                    <a href="${result.url}" target="_blank">
                        <div class="result-header">

                            <div class="result-header-links">
                                <img src="${result.picture}" alt="">
                        
                                <div class="result-links">
                                    <span class="result-domain">${result.domain}</span>
                                    <span class="result-url">${result.url}</span>
                                </div>
                            </div>

                            

                            <h3 class="result-title">${result.title}</h3>

                        </div>

                        <div class="result-body">
                            <p class="result-description">${result.description}</p>
                        </div>
                    </a>
                </div>`
    })

    if (html != ''){
        document.getElementById("results").innerHTML = html
    }
    
}

const search = async(query) => {
    searchInput.value = query.replaceAll("%20", " ");
    
    let url = URL_API + 'search?query=' + query;

    let result = await fetch(url);
    let resultJson = await result.json();

    loadResults(resultJson)

}

 const onClickSearch = () => {
    const queryValidation = searchInput.value.trim();
    if (queryValidation !== ""){
        let query = searchInput.value;
        location.href = './results.html?query=' + query;
    }
    
}


searchInput.addEventListener("keydown", (e) => {
    if (e.key == "Enter"){
        const queryValidation = searchInput.value.trim();
        if (queryValidation !== ""){
            let query = searchInput.value;
            location.href = './results.html?query=' + query;
        } 
    }
    
});

if (query){
    search(query);
}
