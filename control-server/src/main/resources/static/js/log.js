let tags;
let id;
let isDone = false;
window.onload = async () => {
    tags = document.querySelectorAll(".btn_logs");
    // const baseUrl = window.location.href;
    // const pathSegments = baseUrl.split('/');
    // const lastPath = pathSegments.pop();
    const res = await fetch('/logs/sequence/latest');
    id = await res.json();
    ListSetting('dev', id );
    id -= 30;
    buttonSetting();

}



let socket;

const MakeConnectionToWebSocket = () => {
    socket = new WebSocket("ws://도메인/logs");
    socket.onopen = (event) => {
        console.log( "소켓 열림" );
    };
    socket.onmessage = async ( event ) => {
        // console.log( event );
        const json = await JSON.parse( event.data );
        let html = `<div>${json.content}
                    </div>`
        document.querySelector(".log-box").innerHTML += html;
        //이젠 필요 없다.
        // let objDiv = document.getElementById("box");
        // objDiv.scrollTop = objDiv.scrollHeight;
    }
    socket.onclose = (event) => {
        console.log('웹 소켓 닫힘 재연결 시도');
        MakeConnectionToWebSocket();
    };
    scrollSetting();
}

const ListSetting = async ( type, id ) => {
    // console.log('im called', id );
    //원래 로그 저장
    const logBox = document.querySelector(".log-box");
    // const before = logBox.cloneNode(true);
    // console.log( before );
    const url = "/logs/" + type+`?id=${id}&&size=30`;
    const res = await fetch( url );

    const json = await res.json();

    //박스를 비우고
    logBox.innerHTML = "";

    //새로운 것들을 넣고
    json.forEach( el => {
       let html = `<div>${el.content}
                    </div>`
       logBox.innerHTML += html;
    });
    //원래걸 다시 추가
    // logBox.innerHTML += before;

    let objDiv = document.getElementById("box");
    objDiv.scrollTop = objDiv.scrollHeight;

    // console.log( objDiv.scrollHeight );
    MakeConnectionToWebSocket();
}

const ListLoaded = async( type, nid ) => {
    console.log( nid );
    const logBox = document.querySelector(".log-box");
    const before = logBox.innerHTML;

    const url = "/logs/" + type+`?id=${nid}&&size=30`;
    const res = await fetch( url );
    const json = await res.json();

    if( json[ 0 ].id > nid ){
        isDone = true;
        logBox.innerHTML
        return;
    }

    //박스를 비우고
    logBox.innerHTML = `<div>더 이상 로그가 없습니다!</div>`

    //새로운 것들을 넣고
    json.forEach( el => {
        let html = `<div>${el.content}
                    </div>`
        logBox.innerHTML += html;
    });
    let objDiv = document.getElementById("box");
    objDiv.scrollTop = objDiv.scrollHeight;

    //원래걸 다시 추가
    logBox.innerHTML += before;


}

const buttonSetting = () => {
    tags.forEach( (el, index ) => {
        el.addEventListener("click", ( e ) => {
            const val = e.target.getAttribute("id");
            e.target.style.backgroundColor="black";
            e.target.style.color="white";
            // tags[ ( index + 1 ) % 2 ].style.backgroundColor="white";
            // tags[ ( index + 1 ) % 2 ].style.color="black";
            ListSetting( val );
        })
    })
}

const scrollSetting = () => {

    let objDiv = document.getElementById("box");
    objDiv.addEventListener('scroll', (e) => {
        if( isDone ) return;

        if( e.target.scrollTop === 0 ){
            ListLoaded('dev', id );
            id -= 30;
        }

    })
}