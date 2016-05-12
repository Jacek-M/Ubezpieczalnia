(function ($) {
    $.widget('ubezpieczalnia.TableInfo', {
        options: {
            tableId: null,
            tableName: null,
            content: null,
            link: null
        },
        _create: function () {
            this.tableId = this.options.tableId;
            this.tableName = this.options.tableName;
            this.content = this.options.content;
            this.link = this.options.link;
            this.table = null;
            console.log("_create() TableInfo with OPTIONS[" + this.tableId + " " + this.tableName + " " + this.content + "]");
            this._initTableHeader();
            
            $(".table-content").hide();
            this._createTable();
            this._initActionCell();

            this._onCliclListener();
        },
        _createTable: function () {
            var self = this;
            this.table = $('#' + this.tableId).DataTable({
                "language": self._getLanguage(),
                "initComplete": function (settings, json) {
                    $(".table-content").show();
                }

            });

            this.table.on('order.dt search.dt', function () {
                self.table.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            }).draw();

            this.table.column(1).visible(false);

        },
        _onCliclListener: function () {
            this._onClickShowListener();
            this._onClickEditListener();
            this._onClickDeleteListener();
        },
        _onClickShowListener: function () {
            var self = this;
            $('.' + this.content + ' .action-cell #eyeIcon').on("click", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.link + "View.xhtml");
                var url = "/Prodzekt-war/faces/adminPages/" + self.link +"/" + self.link + "View.xhtml";
                var form = $('<form action="' + url + '" method="post">' +
                        '<input type="text" name="post_id" value="' + rowData[1] + '" />' +
                        '</form>');
                $('body').append(form);
                form.submit();

            });
        },
        _onClickEditListener: function () {
            var self = this;
            $('.' + this.content + ' .action-cell #pencilIcon').on("click", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.link + "Edit.xhtml");
                var url = "/Prodzekt-war/faces/adminPages/" + self.link +"/" + self.link + "Edit.xhtml";
                
                 var form = $('<form action="' + url + '" method="post">' +
                        '<input type="text" name="post_id" value="' + rowData[1] + '" />' +
                        '</form>');
                $('body').append(form);
                form.submit();

            });
        },
        _onClickDeleteListener: function () {
            var self = this;
            $('.' + this.content + ' .action-cell #trashIcon').on("click", function () {
                var tr = $(this).closest("tr");
                var rowData = self.table.row(tr).data();
                console.log(rowData);
                console.log("_onClickShowListener() go to ->" + self.content + "Delete.xhtml");
            });
        },
        _initTableHeader: function () {
            $("." + this.content + ' .table-header').html(this.tableName);
        },
        _initActionCell: function () {
            $('.action-cell').append(this._createOsomIcon("eyeIcon", "eye-icon", "fa fa-eye", "Szczegóły"));
            $('.action-cell').append(this._createOsomIcon("pencilIcon", "pencil-icon", "fa fa-pencil", "Edytuj"));
//            $('.action-cell').append(this._createOsomIcon("trashIcon", "trash-icon", "fa fa-trash", "Usuń"));
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