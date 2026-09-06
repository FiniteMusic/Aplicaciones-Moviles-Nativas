const { DateTime } = require("luxon");

const TIMEZONE = "America/Mexico_City";

const parseDueDate = (dateString) => {
    if (!dateString) {
        return null;
    }

    // Validamos que únicamente llegue YYYY-MM-DD
    if (!/^\d{4}-\d{2}-\d{2}$/.test(dateString)) {
        return null;
    }

    const date = DateTime.fromFormat(
        dateString,
        "yyyy-MM-dd",
        {
            zone: TIMEZONE
        }
    );

    // Verificamos que realmente sea una fecha válida
    if (!date.isValid) {
        return null;
    }

    // La fecha límite será a las 23:59:00 CDMX
    return date
        .set({
            hour: 23,
            minute: 59,
            second: 0,
            millisecond: 0
        })
        .toUTC()
        .toJSDate();
};

module.exports = {
    parseDueDate,
    TIMEZONE
};