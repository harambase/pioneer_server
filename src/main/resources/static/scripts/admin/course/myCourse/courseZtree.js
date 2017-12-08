var coursePath;
var courseZtreeObj;
var courseZtreeRootNodes = [{id: 1, pId: 0, name: "课程列表", isParent: true, path: "", info: "/", createTime: ""}];
var courseZtreeSetting = {
    async: {
        enable: true,
        type: "get",
        url: "/course/zTree/list",
        // autoParam: ["info"],
        dataFilter: courseZtreeFilter
    },
    edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
    },
    // callback: {
    //     onClick: courseZtreeOnClick
    // },
    data: {
        keep: {
            parent: true,
            leaf: true
        },
        simpleData: {
            enable: true
        }
    }
};
var clickNode;
// function courseZtreeOnClick(event, treeId, treeNode) {
//     clickNode = treeNode;
//     courseZtreeObj.expandNode(treeNode, true, false, true);//展开当前节点
//     if (clickNode.isdir === "1") {
//         var uri = "";
//         if (treeNode.path === "" && treeNode.name === "root") {
//             uri = "/";
//         } else {
//             uri = buildUri(treeNode.path, treeNode.name);
//         }
//         coursePath = uri;
//     }
// }

function courseZtreeFilter(treeId, parentNode, childNodes) {
    childNodes = childNodes.data;
    console.log(childNodes);
    console.log(parentNode);
    // var retChildNodes = [];
    // if (!childNodes) return null;
    // var length = childNodes.length;
    // for (var i = 0;i < length; i++) {
    //     var uri = buildUri(childNodes[i].path, childNodes[i].name);
    //     if (childNodes[i].isdir === "1") {
    //         childNodes[i].isParent = true;
    //         childNodes[i].uri = uri;
    //         retChildNodes.push(childNodes[i]);
    //     }
    // }
    // return retChildNodes;
}