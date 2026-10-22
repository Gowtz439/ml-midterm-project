#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Safely copies a source string into a destination buffer with boundary checks.
 *
 * @param src      Pointer to source string.
 * @param dest     Pointer to destination buffer.
 * @param max_len  Maximum allocated size of the destination buffer.
 * @return         0 on success, negative error code on invalid parameters or overflow.
 */
int safe_buffer_copy(const char *src, char *dest, size_t max_len) {
    if (src == NULL || dest == NULL || max_len == 0) {
        return -1;
    }

    size_t src_len = strnlen(src, max_len);

    if (src_len >= max_len) {
        return -2;
    }

    memcpy(dest, src, src_len);
    dest[src_len] = '\0';

    return 0;
}

/**
 * Validates whether an integer lies within an inclusive range.
 */
int is_valid_range(int value, int min, int max) {
    if (min > max) {
        return 0;
    }
    return (value >= min && value <= max) ? 1 : 0;
}
