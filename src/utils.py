"""
Data validation and utility module for house price features.
Implements defensive input checks and type boundaries.
"""

from typing import Dict, Any, Optional
import math


class DataValidationError(ValueError):
    """Raised when house feature values fail domain validation."""
    pass


def validate_house_features(area: float, bedrooms: int, age: int) -> Dict[str, Any]:
    """
    Validate house prediction input features against domain boundaries.

    Args:
        area (float): Square footage of the property. Must be > 0.
        bedrooms (int): Number of bedrooms. Must be an integer >= 1.
        age (int): Property age in years. Must be >= 0.

    Returns:
        Dict[str, Any]: Sanitized and validated feature dictionary.

    Raises:
        DataValidationError: If any value is out of logical domain bounds.
        TypeError: If types cannot be cast to expected numeric formats.
    """
    # 1. Type and boundary verification
    try:
        clean_area = float(area)
        clean_bedrooms = int(bedrooms)
        clean_age = int(age)
    except (ValueError, TypeError) as cast_err:
        raise DataValidationError(f"Invalid numeric input provided: {cast_err}")

    # 2. Domain logic guards
    if math.isnan(clean_area) or math.isinf(clean_area) or clean_area <= 0.0:
        raise DataValidationError(f"Property area must be a positive number. Got: {clean_area}")

    if clean_bedrooms < 1 or clean_bedrooms > 50:
        raise DataValidationError(f"Bedrooms count out of realistic range (1-50). Got: {clean_bedrooms}")

    if clean_age < 0 or clean_age > 300:
        raise DataValidationError(f"Property age out of realistic range (0-300). Got: {clean_age}")

    return {
        "area": round(clean_area, 2),
        "bedrooms": clean_bedrooms,
        "age": clean_age,
        "is_valid": True
    }


def format_currency(amount: float, symbol: str = "₹") -> str:
    """
    Safely format numeric currency amounts.
    """
    if not isinstance(amount, (int, float)) or math.isnan(amount) or math.isinf(amount):
        return f"{symbol} 0.00"
    
    clean_val = max(0.0, float(amount))
    return f"{symbol} {clean_val:,.2f}"
