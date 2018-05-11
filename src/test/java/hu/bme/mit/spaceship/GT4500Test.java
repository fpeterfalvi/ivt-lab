package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

/*
Új tesztesetek:

1) SINGLE mód, első store üres, de a másodikban van még egy töltény -> lövés sikeres

2) SINGLE mód, mindkét store üres -> lövés sikertelen

3) ALL mód, első store-ban van töltény, másodikban nincs -> lövés sikeres

4) ALL mód, első store-ban nincs töltény, de a másodikban van -> lövés sikeres

5) ALL mód, mindkét store üres -> lövés sikertelen

*/

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockFirstTorpedoStore;
  private TorpedoStore mockSecondTorpedoStore;

  @Before
  public void init(){
    mockFirstTorpedoStore = mock(TorpedoStore.class);
    mockSecondTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(mockFirstTorpedoStore, mockSecondTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }


  // 1-es új teszt
  @Test
  public void fireTorpedo_Single_FirstStoreIsEmpty(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(false);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    // Verification
    verify(mockFirstTorpedoStore, times(1)).isEmpty();
    verify(mockFirstTorpedoStore, times(0)).fire(1);
    verify(mockSecondTorpedoStore, times(1)).isEmpty();
    verify(mockSecondTorpedoStore, times(1)).fire(1);
  }

  // 2-es új teszt
  @Test
  public void fireTorpedo_Single_BothStoresAreEmpty(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(false);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(true);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  // 3-as új teszt
  @Test
  public void fireTorpedo_All_SecondStoreIsEmpty(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(true);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  // 4-es új teszt
  @Test
  public void fireTorpedo_All_FirstStoreIsEmpty(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(false);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  // 5-ös új teszt
  @Test
  public void fireTorpedo_All_BothStoresAreEmpty(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(false);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(true);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  // 6-os új teszt
  @Test
  public void fireTorpedo_Single_PrimaryFiredLast(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(true);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(false);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean wasPrimaryFiredLast = ship.getWasPrimaryFiredLast();

    // Assert
    assertEquals(false, wasPrimaryFiredLast);
  }

  // 7-es új teszt
  @Test
  public void fireTorpedo_Single_FireFirstThenSecond(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1 && result2);
  }

}
