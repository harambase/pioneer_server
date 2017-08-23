// 数据接入流量
function accessFlow(divUrl, dataInfo) {
    var myChart = echarts.init(document.getElementById(divUrl));
    var option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dataInfo.dates
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        series: [
            {
                name: dataInfo.unit,
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: '#8ab3c7'
                    },
                    lineStyle: "smooth"
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#cdd8e1'
                        }, {
                            offset: 1,
                            color: '#cdd8e1'
                        }])
                    }
                },
                data: dataInfo.values
            }
        ]
    };

    myChart.setOption(option);
    return myChart;
}
/*数据接入次数*/
function accessTimes(divUrl, dataInfo) {
    var myChart = echarts.init(document.getElementById(divUrl));
    var option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dataInfo.dates
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        series: [
            {
                name: dataInfo.unit,
                type: 'line',
                smooth: true,
                symbol: 'emptyCircle',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: '#8ab3c7'
                    },
                    lineStyle: "smooth"
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#6eb5e5'
                        }, {
                            offset: 1,
                            color: '#6eb5e5'
                        }])
                    }
                },
                data: dataInfo.values
            }
        ]
    };

    myChart.setOption(option);
    return myChart;
}


/*数据传输实时流量*/
function transformCurrent(divUrl, dataInfo) {
    var myChart = echarts.init(document.getElementById(divUrl));
    var option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dataInfo.dates
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        series: [
            {
                name: "历史流量",
                type: 'line',
                smooth: true,
                symbol: 'emptyCircle',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: '#fff',
                        borderWidth: 5
                    },
                    lineStyle: "smooth"
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#cdd8e1'
                        }, {
                            offset: 1,
                            color: '#cdd8e1'
                        }])
                    }
                },
                data: dataInfo.values
            }
        ]
    };
    myChart.setOption(option);
    return myChart;
}

/*数据接入管理*/
function dataAccessManage(divUrl, dataInfo) {
    var myChart = echarts.init(document.getElementById(divUrl));
    // console.log(dataInfo);
    var seriesData = [];
    for (var i = 0; i < dataInfo.dataType.length; i++) {
        seriesData[i] = {};
        seriesData[i].name = dataInfo.dataType[i];
        seriesData[i].data = dataInfo.dataDetails[i];
        seriesData[i].type = "line";
    }
    // seriesData = JSON.stringify(seriesData);
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: dataInfo.dataType
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dataInfo.dataX
        },
        yAxis: {
            type: 'value'
        },
        series: seriesData
        // series: [
        //     {
        //         name:dataInfo.dataType[0],
        //         type:'line',
        //         stack: '总量',
        //         data:dataInfo.dataDetails[0],
        //         lineStyle:{
        //             normal:{
        //                 color:"#d8bce2"
        //             }
        //         }
        //     },
        //     {
        //         name:dataInfo.dataType[1],
        //         type:'line',
        //         stack: '总量',
        //         data:dataInfo.dataDetails[1],
        //         lineStyle:{
        //             normal:{
        //                 color:"#a4bce0"
        //             }
        //         }
        //     },
        //     {
        //         name:dataInfo.dataType[2],
        //         type:'line',
        //         stack: '总量',
        //         data:dataInfo.dataDetails[2],
        //         lineStyle:{
        //             normal:{
        //                 color:"#7fcd81"
        //             }
        //         }
        //     },
        //     {
        //         name:dataInfo.dataType[3],
        //         type:'line',
        //         stack: '总量',
        //         data:dataInfo.dataDetails[3],
        //         lineStyle:{
        //             normal:{
        //                 color:"#e2949f"
        //             }
        //         }
        //     },
        //     {
        //         name:dataInfo.dataType[4],
        //         type:'line',
        //         stack: '总量',
        //         data:dataInfo.dataDetails[4],
        //         lineStyle:{
        //             normal:{
        //                 color:"#f6a123"
        //             }
        //         }
        //     }
        // ]
    };
    myChart.setOption(option);
    return myChart;
}

/*实例用户使用top20*/
function exampleUserTop(divUrl,data1,data2) {
    var myChart = echarts.init(document.getElementById(divUrl));
    var option = {
        title : {
            text: 'Pioneer User Chart',
            subtext: 'Realtime',
            x:'center'
        },
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
                roseType : 'area',
                data:data2
            }
        ]
    };

    myChart.setOption(option);
    return myChart;
}

function relation(divUrl,data1,data2) {
    var myChart = echarts.init(document.getElementById(divUrl));
    myChart.showLoading();
    $.get(basePath+'/static/data/static_graph_sample.gexf', function (xml) {
        myChart.hideLoading();

        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 4; i++) {
            categories[i] = {
                name: '类目' + i
            };
        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.value = node.symbolSize;
            node.symbolSize /= 1.5;
            node.label = {
                normal: {
                    show: node.symbolSize > 10
                }
            };
            node.category = node.attributes.type;
        });
        option = {
            title: {
                text: 'Pioneer SFC Relations',
                subtext: 'Student-Faculty-Course',
                top: 'top',
                left: 'center'
            },
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
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'circular',
                    circular: {
                        rotateLabel: true
                    },
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: true,
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