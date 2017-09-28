function showLoading(id) {
    var loader =
        {
            width: 100,
            height: 100,
            stepsPerFrame: 1,
            trailLength: 1,
            pointDistance: .025,
            strokeColor: '#05E2FF',
            fps: 20,
            setup: function() {this._.lineWidth = 2;},
            step: function(point, index) {
                var cx = this.padding + 50,
                    cy = this.padding + 50,
                    _ = this._,
                    angle = (Math.PI/180) * (point.progress * 360);
                this._.globalAlpha = Math.max(.5, this.alpha);
                _.beginPath();
                _.moveTo(point.x, point.y);
                _.lineTo(
                    (Math.cos(angle) * 35) + cx,
                    (Math.sin(angle) * 35) + cy
                );
                _.closePath();
                _.stroke();
                _.beginPath();
                _.moveTo(
                    (Math.cos(-angle) * 32) + cx,
                    (Math.sin(-angle) * 32) + cy
                );
                _.lineTo(
                    (Math.cos(-angle) * 27) + cx,
                    (Math.sin(-angle) * 27) + cy
                );
                _.closePath();
                _.stroke();
            },
            path: [
                ['arc', 50, 50, 40, 0, 360]
            ]
        };


    var d, a, container = document.getElementById(id);

    d = document.createElement('div');
    d.className = 'l';
    a = new Sonic(loader);
    d.appendChild(a.canvas);
    container.appendChild(d);

    a.play();
}