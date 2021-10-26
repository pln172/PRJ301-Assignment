/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createPagger(url, div, before, pageIndex, after, gap, totalPage) {
    var container = document.getElementById(div);
    if (totalPage > 1) {
        if (pageIndex - 1 > 0) {
            container.innerHTML += '<a class="btn" href="'+url+'?page=' + before + '"><<</a>';
        } else {
            container.innerHTML += '<a class="btn" onclick="return false;"><<</a>';
        }

        if (pageIndex - gap > 1) {
            container.innerHTML += '<a href="'+url+'?page=1">First</a>';
        }

        for (var i = pageIndex - gap; i < pageIndex; i++) {
            if (i > 0) {
                container.innerHTML += '<a href="'+url+'?page=' + i + '">' + i + '</a>';
            }
        }

        container.innerHTML += '<span>' + pageIndex + '</span>';

        for (var i = pageIndex + 1; i <= pageIndex + gap; i++) {
            if (i <= totalPage) {
                container.innerHTML += '<a href="'+url+'?page=' + i + '">' + i + '</a>';
            }
        }

        if (pageIndex + gap < totalPage) {
            container.innerHTML += '<a href="'+url+'?page=' + totalPage + '">Last</a>';
        }

        if (pageIndex + 1 <= totalPage) {
            container.innerHTML += '<a class="btn" href="'+url+'?page=' + after + '">>></a>';
        } else {
            container.innerHTML += '<a class="btn" onclick="return false;">>></a>';
        }
    }
}
