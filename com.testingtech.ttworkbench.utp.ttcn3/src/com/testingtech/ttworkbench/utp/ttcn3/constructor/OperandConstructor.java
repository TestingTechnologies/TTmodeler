/*
 * ----------------------------------------------------------------------------
 *  (C) Copyright Testing Technologies, 2001-2012.  All Rights Reserved.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     Testing Technologies - initial API and implementation
 *
 *  All copies of this program, whether in whole or in part, and whether
 *  modified or not, must display this and all other embedded copyright
 *  and ownership notices in full.
 *
 *  See the file COPYRIGHT for details of redistribution and use.
 *
 *  You should have received a copy of the COPYRIGHT file along with
 *  this file; if not, write to the Testing Technologies,
 *  Michaelkirchstr. 17/18, 10179 Berlin, Germany.
 *
 *  TESTING TECHNOLOGIES DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS
 *  SOFTWARE. IN NO EVENT SHALL TESTING TECHNOLOGIES BE LIABLE FOR ANY
 *  SPECIAL, DIRECT, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *  WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN
 *  AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
 *  ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 *  THIS SOFTWARE.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 *  EITHER EXPRESSED OR IMPLIED, INCLUDING ANY KIND OF IMPLIED OR
 *  EXPRESSED WARRANTY OF NON-INFRINGEMENT OR THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 * -----------------------------------------------------------------------------
 */
package com.testingtech.ttworkbench.utp.ttcn3.constructor;

import com.testingtech.muttcn.kernel.Expression;
import com.testingtech.ttworkbench.utp.core.TTmodelerConsts;

public class OperandConstructor {

  public Expression construct(String operandName) {

    Expression operand;

    if (operandName.equals(TTmodelerConsts.ANY)) {
      operand = new NullaryOpConstructor().construct(TTmodelerConsts.ANY_TIMER);
    }
    else if (operandName.equals(TTmodelerConsts.ALL)) {
      operand = new NullaryOpConstructor().construct(TTmodelerConsts.ALL_TIMER);
    }
    else {
      operand = new ExpressionConstructor().construct(operandName);
    }

    return operand;
  }

  /**
   * this method handles special operands which can be of type "any" or "all".
   * @param operandName
   * @param operandType
   * @return
   */
  public Expression construct(String operandName, int operandType) {

    // in the future, a second branch might be added here for components
    if (operandType == TTmodelerConsts.TIMER_OPERAND_TYPE) {
      return constructTimerOperand(operandName);
    }
    else {
      throw new RuntimeException(Messages.OperandConstructor_UnexpectedOperandType);
    }
  }

  private Expression constructTimerOperand(String operandName) {

    if (operandName.equals(TTmodelerConsts.ANY)) {
      return new NullaryOpConstructor().construct(TTmodelerConsts.ANY_TIMER);
    }
    else if (operandName.equals(TTmodelerConsts.ALL)) {
      return new NullaryOpConstructor().construct(TTmodelerConsts.ALL_TIMER);
    }
    else {      
      return construct(operandName);
    }
  }  
}
