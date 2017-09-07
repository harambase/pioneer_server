
function exampleUserTop(divUrl,data1,data2) {
    var myChart = echarts.init(document.getElementById(divUrl));
    var option = {
        // title : {
        //     text: 'Pioneer User Chart',
        //     subtext: 'Realtime',
        //     x:'center'
        // },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:['1','2','3','4','5','6','7','8']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'User Counts',
                type:'pie',
                radius : [20, 135],
                center : ['25%', '50%'],
                roseType : 'radius',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:data1
            },
            {
                name:'Gender Counts',
                type:'pie',
                radius : [30, 135],
                center : ['75%', '50%'],
                roseType : 'radius',
                data:data2
            }
        ]
    };

    myChart.setOption(option);
    return myChart;
}

function relation(divUrl) {
    var myChart = echarts.init(document.getElementById(divUrl));
    myChart.showLoading();
    $.get(basePath+'/static/data/static_graph_sample.gexf', function (xml) {
        myChart.hideLoading();

        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [{name: '课程'},{name: '学生'},{name:'教师'}];

        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.value = (node.symbolSize-10)/5;
            node.label = {
                normal: {
                    show: node.symbolSize > 10
                }
            };
            node.category = node.attributes.type;
        });
        option = {
            // title: {
            //     text: 'Pioneer SFC Relations',
            //     subtext: 'Student-Faculty-Course',
            //     top: 'bottom',
            //     left: 'right'
            // },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            series: [
                {

                    type: 'graph',
                    layout: 'circular',
                    circular: {
                        rotateLabel: true
                    },
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: false,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.3
                        }
                    }
                }
            ]
        };

        myChart.setOption(option);
    }, 'xml');
}

function relation2(divUrl){
    var myChart = echarts.init(document.getElementById(divUrl));
    myChart.showLoading();
    $.get(basePath+'/static/data/static_graph_sample.gexf', function (xml) {
        myChart.hideLoading();

        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [{name: '课程'},{name: '学生'},{name:'教师'}];

        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.value = node.symbolSize;
            node.label = {
                normal: {
                    show: node.symbolSize > 10
                }
            };
            node.category = node.attributes.type;
        });
        option = {
            // title: {
            //     text: 'Les Miserables',
            //     subtext: 'Default layout',
            //     top: 'bottom',
            //     left: 'right'
            // },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animationDuration: 1500,
            animationEasingUpdate: 'quinticInOut',
            series : [
                {
                    // name: 'Les Miserables',
                    type: 'graph',
                    layout: 'none',
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: false,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.3
                        }
                    }
                }
            ]
        };

        myChart.setOption(option);
    }, 'xml');
}