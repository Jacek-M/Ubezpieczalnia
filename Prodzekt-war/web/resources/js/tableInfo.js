(function ($) {
    $.widget('ubezpieczalnia.TableInfo', {
        options: {
            tableId: null,
            tableName: null,
            content: null,
            tableActions: null,
            link: null,
            action: null
        },
        _create: function () {
            this.tableId = this.options.tableId;
            this.tableName = this.options.tableName;
            this.content = this.options.content;
            this.link = this.options.link;
            this.tableActions = this.options.tableActions;
            this.action = this.options.action;
            this.table = null;
            console.log("_create() TableInfo with OPTIONS[" + this.tableId + " " + this.tableName + " " + this.content + "]");
            this._initTableHeader();
            $(".table-content").hide();
            this._createTable();
            this._onClickListener();
        },
        _createTable: function () {
            var self = this;
            this.table = $('#' + this.tableId).DataTable({
                "language": self._getLanguage(),
                "initComplete": function (settings, json) {
                    $(".table-content").show();
                }
            });
            var colIndex = this._getTableColumnIndex("Akcja", self.table.row(0).data().length);
            this.table.on('order.dt search.dt', function () {
                self.table.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
                self.table.column(colIndex, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
                    cell.innerHTML = self._createOsomIcon("eyeIcon", "eye-icon", "fa fa-eye", "Szczegóły");
                    if (self.tableActions === "admin") {
                        if (self.tableName === "Umowy")
                            cell.innerHTML += self._createOsomIcon("checkIcon", "pencil-check", "fa fa-check", "Zatwierdź");
                        else
                            cell.innerHTML += self._createOsomIcon("pencilIcon", "pencil-icon", "fa fa-pencil", "Edytuj");
                    }
                    if (self.tableActions === "worker") {
                        if (self.tableId === "tableIncidentWorkerAdd")
                            cell.innerHTML += self._createOsomIcon("checkIcon", "pencil-check", "fa fa-check", "Pobierz");
                        if (self.tableId === "tableIncidentWorker")
                            cell.innerHTML += self._createOsomIcon("checkIcon", "pencil-check", "fa fa-check", "Zakończ");
                    }
                });
            }).draw();
            this.table.column(1).visible(false);
            this._validate();
        },
        _validate: function () {
            var tableData = this.table.rows().data();
            if (tableData.length > 0) {
                var x = 0;
                for (var i = 0; i < tableData[0].length; i++) {
                    if (tableData[0][i] === null || tableData[0][i].length <= 0) {
                        x++;
                    }
                }
                if (x > 3) {
                    this.table.rows().remove().draw();
                }
            }
        },
        _onClickListener: function () {
            this._onClickShowListener();
            this._onClickEditListener();
            this._onClickDeleteListener();
            this._onClickCustomListener();
        },
        _onClickShowListener: function () {
            var self = this;
            $('.' + this.content).on("click", " tbody td .eye-icon", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.link + "View.xhtml");
                switch (self.tableActions) {
                    case "admin":
                    {
                        var pagesContent = "adminPages";
                        break;
                    }
                    case "client":
                    {
                        var pagesContent = "customerPages";
                        break;
                    }
                    case "worker":
                    {
                        var pagesContent = "why";
                        break;
                    }
                }
//                var pagesContent = self.tableActions === "admin" ? "adminPages" : "customerPages";
                var url = "/Prodzekt-war/faces/" + pagesContent + "/" + self.link + "/" + self.link + "View.xhtml";
                var form = $('<form action="' + url + '" method="post">' +
                        '<input type="text" name="post_id" value="' + rowData[1] + '" />' +
                        '</form>');
                $('body').append(form);
                form.submit();
            });
        },
        _onClickEditListener: function () {
            var self = this;
            $('.' + this.content).on("click", " tbody td .pencil-icon", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.link + "Edit.xhtml");
                switch (self.tableActions) {
                    case "admin":
                    {
                        var pagesContent = "adminPages";
                        break;
                    }
                    case "client":
                    {
                        var pagesContent = "customerPages";
                        break;
                    }
                    case "worker":
                    {
                        var pagesContent = "why";
                        break;
                    }
                }
                var url = "/Prodzekt-war/faces/" + pagesContent + "/" + self.link + "/" + self.link + "Edit.xhtml";
                var form = $('<form action="' + url + '" method="post">' +
                        '<input type="text" name="post_id" value="' + rowData[1] + '" />' +
                        '</form>');
                $('body').append(form);
                form.submit();
            });
        },
        _onClickCustomListener: function () {
            var self = this;
            $('.' + this.content).on("click", " tbody td .pencil-check", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.link + ".xhtml");
                switch (self.tableActions) {
                    case "admin":
                    {
                        var pagesContent = "adminPages";
                        break;
                    }
                    case "client":
                    {
                        var pagesContent = "customerPages";
                        break;
                    }
                    case "worker":
                    {
                        var pagesContent = "why";
                        break;
                    }
                }
                var url = "/Prodzekt-war/faces/" + pagesContent + "/" + self.link + "/" + self.link + ".xhtml";
                var form = $('<form action="' + url + '" method="post">' +
                        '<input type="text" name="post_id" value="' + rowData[1] + '" />' +
                        '<input type="text" name="post_type" value="' + self.action + '" />' +
                        '</form>');
                $('body').append(form);
                form.submit();
            });
        },
        _onClickDeleteListener: function () {
            var self = this;
            $('.' + this.content).on("click", " tbody td .trash-icon", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.content + "Delete.xhtml");
            });
        },
        _initTableHeader: function () {
            $("." + this.content + ' .table-header').html(this.tableName);
        },
        _getTableColumnIndex: function (columnName, length) {
            for (var i = 0; i < length; i++) {
                var header = $(this.table.column(i).header()).text();
                if (header === columnName)
                    return i;
                return -1;
            }
        },
        _createOsomIcon: function (id, className, osomClass, tooltip) {
            var html = "<a href='javascript:void(0);' id='" + id + "' class='" + className + "' title='" + tooltip + "'><i class='" + osomClass + "' aria-hidden=\"true\"></i> </a>";
            return html;
        },
        _getLanguage: function () {
            var lang = {
                "decimal": "",
                "emptyTable": "Brak rekordów w tabeli",
                "info": "Wyświetlane _START_ - _END_ z _TOTAL_ wszystkich rekordów",
                "infoEmpty": "Brak rekordów do wyświetlenia",
                "infoFiltered": "",
                "infoPostFix": "",
                "thousands": ",",
                "lengthMenu": "Pokaż _MENU_ rekordów na stronę",
                "loadingRecords": "Wczytywanie...",
                "processing": "Przetwarzanie...",
                "search": "Szukaj:",
                "zeroRecords": "Brak pasujących rekordów",
                "paginate": {
                    "first": "Pierwsza",
                    "last": "Ostatnia",
                    "next": "Następna",
                    "previous": "Poprzednia"
                },
                "aria": {
                    "sortAscending": ": aktywne sortowanie rosnące",
                    "sortDescending": ": aktywne sortowanie malejące"
                }
            };
            return lang;
        }
    }); // END OF WIDGET

}(jQuery));